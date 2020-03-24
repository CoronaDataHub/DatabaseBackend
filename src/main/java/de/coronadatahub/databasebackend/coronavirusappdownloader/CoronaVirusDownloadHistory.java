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
import de.coronadatahub.databasebackend.coronavirusappdownloader.models.history.GetHistory;
import de.coronadatahub.databasebackend.database.RethinkDBAPI;
import de.coronadatahub.databasebackend.database.models.coronavirusapp.DataHistory;
import de.coronadatahub.databasebackend.database.models.coronavirusapp.Place;
import de.coronadatahub.databasebackend.math.DarkFigure;
import de.coronadatahub.databasebackend.services.CoronavriusappRestAPI;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CoronaVirusDownloadHistory implements Runnable {

    private RethinkDBAPI rethinkDBAPI;
    private CoronavriusappRestAPI coronavriusappRestAPI;
    private Gson gson;

    public CoronaVirusDownloadHistory(RethinkDBAPI rethinkDBAPI, Gson gson, Config config) {
        this.rethinkDBAPI = rethinkDBAPI;
        this.coronavriusappRestAPI = new CoronavriusappRestAPI(config.getCoronvirusaappapikey());
        this.gson = gson;
    }

    @Override
    public void run() {
        getPlaces().forEach(place -> {
            GetHistory getHistory = gson.fromJson(coronavriusappRestAPI.getHistory(place.getObjectid()), GetHistory.class);
            getHistory.getData().getHistory().forEach(history -> {
                DataHistory dataHistory = new DataHistory();
                dataHistory.setTime(getDate(history.getDay()));
                dataHistory.setInfected(Double.parseDouble(history.getInfected()));
                dataHistory.setDead(Double.parseDouble(history.getDead()));
                dataHistory.setRecovered(Double.parseDouble(history.getRecovered()));
                dataHistory.setSick(Double.parseDouble(history.getSick()));
                dataHistory.setDarkFigure(DarkFigure.calculate(Double.parseDouble(history.getDead()), 3));
                place.getDataHistories().add(dataHistory);
            });
            updatePlace(place);
        });
    }

    private long getDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        try {
            Date time = sdf.parse(date);
            return time.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private ArrayList<Place> getPlaces() {
        Result<Place> run = rethinkDBAPI.getR().db("Datahub").table("Coronavirusapp").run(rethinkDBAPI.getConnect(), Place.class);
        return (ArrayList<Place>) run.toList();
    }

    private void updatePlace(Place place){
        deletePlace(place.getObjectid());
        insert(place);
    }

    private void deletePlace(String objectid){
        rethinkDBAPI.getR().db("Datahub").table("Coronavirusapp").filter(row -> row.g("objectid").eq(objectid)).delete().run(rethinkDBAPI.getConnect());
    }

    private void insert(Place place){
        rethinkDBAPI.getR().db("Datahub").table("Coronavirusapp").insert(place).run(rethinkDBAPI.getConnect());
    }
}
