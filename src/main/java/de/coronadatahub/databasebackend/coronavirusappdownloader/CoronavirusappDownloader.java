/*
 * *
 *    ____                                          ____            _             _   _           _
 *   / ___|   ___    _ __    ___    _ __     __ _  |  _ \    __ _  | |_    __ _  | | | |  _   _  | |__
 *  | |      / _ \  | '__|  / _ \  | '_ \   / _` | | | | |  / _` | | __|  / _` | | |_| | | | | | | '_ \
 *  | |___  | (_) | | |    | (_) | | | | | | (_| | | |_| | | (_| | | |_  | (_| | |  _  | | |_| | | |_) |
 *   \____|  \___/  |_|     \___/  |_| |_|  \__,_| |____/   \__,_|  \__|  \__,_| |_| |_|  \__,_| |_.__/
 *
 *  	CoronaDataHub ist ein Projekt welches im Rahmen von der Initiative #WirVSVirus-Hackathon vom 20-22 März 2020 ins Leben gerufen wurde.
 *
 *
 */

package de.coronadatahub.databasebackend.coronavirusappdownloader;

import com.google.gson.Gson;
import com.rethinkdb.net.Result;
import de.coronadatahub.databasebackend.config.models.Config;
import de.coronadatahub.databasebackend.coronavirusappdownloader.models.places.GetPlaces;
import de.coronadatahub.databasebackend.database.RethinkDBAPI;
import de.coronadatahub.databasebackend.database.models.coronavirusapp.DataHistory;
import de.coronadatahub.databasebackend.database.models.coronavirusapp.Place;
import de.coronadatahub.databasebackend.math.DarkFigure;
import de.coronadatahub.databasebackend.services.CoronavriusappRestAPI;

import java.util.ArrayList;
import java.util.Comparator;

public class CoronavirusappDownloader implements Runnable {

    private CoronavriusappRestAPI coronavriusappRestAPI;
    private Gson gson;
    private RethinkDBAPI rethinkDBAPI;

    public CoronavirusappDownloader(Config config, Gson gson, RethinkDBAPI rethinkDBAPI) {
        this.rethinkDBAPI = rethinkDBAPI;
        this.gson = gson;
        coronavriusappRestAPI = new CoronavriusappRestAPI(config.getCoronvirusaappapikey());
    }

    @Override
    public void run() {
        System.out.println("Run: CoronavirusappDownloader");
        long time = System.currentTimeMillis();
        GetPlaces getPlaces = gson.fromJson(coronavriusappRestAPI.getPlaces(), GetPlaces.class);
        getPlaces.getData().forEach(placesData -> {
            String objectid = placesData.getId();
            Place place = getPlace(objectid);
            if (place == null) {
                place = new Place();
                place.setObjectid(objectid);
                place.setName(placesData.getName());
                place.setCountry(placesData.getCountry());
                place.setLongitude(placesData.getLongitude());
                place.setLatitude(placesData.getLatitude());
                place.setDataHistories(new ArrayList<>());
            } else {
                rethinkDBAPI.getR().db("Datahub").table("Coronavirusapp").filter(row -> row.g("objectid").eq(objectid)).delete().run(rethinkDBAPI.getConnect());
            }
            place.setLastUpdated(placesData.getLastUpdated());
            DataHistory dataHistory = new DataHistory();
            dataHistory.setTime(time);
            dataHistory.setInfected(Double.parseDouble(placesData.getInfected()));
            dataHistory.setRecovered(Double.parseDouble(placesData.getRecovered()));
            dataHistory.setDead(Double.parseDouble(placesData.getDead()));
            dataHistory.setSick(Double.parseDouble(placesData.getSick()));
            dataHistory.setDarkFigure(DarkFigure.calculate(Double.parseDouble(placesData.getDead()), 3));
            place.getDataHistories().add(dataHistory);
            place.getDataHistories().sort(Comparator.comparingDouble(DataHistory::getTime));

            rethinkDBAPI.getR().db("Datahub").table("Coronavirusapp").insert(place).run(rethinkDBAPI.getConnect());


        });
    }

    private Place getPlace(String objectid) {
        Result<Place> run = rethinkDBAPI.getR().db("Datahub").table("Coronavirusapp").filter(row -> row.g("objectid").eq(objectid)).run(rethinkDBAPI.getConnect(), Place.class);
        if (run.hasNext()) {
            return run.next();
        } else {
            return null;
        }
    }
}
