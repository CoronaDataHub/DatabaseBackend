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

package de.coronadatahub.databasebackend.rkidownloader;

import com.google.gson.Gson;
import com.rethinkdb.net.Result;
import de.coronadatahub.databasebackend.config.models.Config;
import de.coronadatahub.databasebackend.coutiescache.CountiesChache;
import de.coronadatahub.databasebackend.coutiescache.models.Search;
import de.coronadatahub.databasebackend.database.RethinkDBAPI;
import de.coronadatahub.databasebackend.database.models.rki.Counties;
import de.coronadatahub.databasebackend.database.models.rki.CountiesData;
import de.coronadatahub.databasebackend.rkidownloader.models.Attributes;
import de.coronadatahub.databasebackend.rkidownloader.models.RKICounties;
import de.coronadatahub.databasebackend.services.JsonReaderService;

import java.io.IOException;
import java.util.ArrayList;

public class RKIDownloader implements Runnable {

    private JsonReaderService jsonReaderService;
    private Config config;
    private Gson gson;
    private RethinkDBAPI rethinkDBAPI;
    private CountiesChache countiesChache;

    public RKIDownloader(Config config, Gson gson, RethinkDBAPI rethinkDBAPI) {
        this.config = config;
        this.rethinkDBAPI = rethinkDBAPI;
        this.gson = gson;
        jsonReaderService = new JsonReaderService();
        this.countiesChache = new CountiesChache(gson);
    }

    @Override
    public void run() {
        System.out.println("Run: RKIDownloader");
        try {
            uploadToRethinkDB(gson.fromJson(jsonReaderService.readJsonFromUrl(config.getRkiURL()), RKICounties.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void uploadToRethinkDB(RKICounties rkiCounties) {
        long currentTime = System.currentTimeMillis();
        rkiCounties.getFeatures().forEach(features -> {
            Attributes attributes = features.getAttributes();
            double objectid = attributes.getOBJECTID();
            Counties counties = getCounties(objectid);
            if (counties == null) {
                counties = new Counties();
                counties.setOBJECTID(attributes.getOBJECTID());
                counties.setGEN(attributes.getGEN());
                counties.setBEZ(attributes.getBEZ());
                counties.setBL(attributes.getBL());
                counties.setBL_ID(Double.parseDouble(attributes.getBL_ID()));
                counties.setCounty(attributes.getCounty());
                counties.setCountiesData(new ArrayList<>());

                Search search = countiesChache.getContent(counties.getGEN().replaceAll(" ", "+")).getSearches().get(0);

                if (search != null){
                    counties.setLatitude(Double.parseDouble(search.getLat()));
                    counties.setLongitude(Double.parseDouble(search.getLon()));
                }
            } else {
                rethinkDBAPI.getR().db("Datahub").table("Counties").filter(row -> row.g("objectid").eq(objectid)).delete().run(rethinkDBAPI.getConnect());
            }

            CountiesData countiesData = new CountiesData();
            countiesData.setTime(currentTime);
            countiesData.setDeath_rate(attributes.getDeath_rate());
            countiesData.setCases(attributes.getCases());
            countiesData.setDeaths(attributes.getDeaths());
            countiesData.setCases_per_100k(attributes.getCases_per_100k());
            countiesData.setCases_per_population(attributes.getCases_per_population());
            counties.getCountiesData().add(countiesData);

            rethinkDBAPI.getR().db("Datahub").table("Counties").insert(counties).run(rethinkDBAPI.getConnect());
        });
    }

    private Counties getCounties(double objectid) {
        Result<Counties> run = rethinkDBAPI.getR().db("Datahub").table("Counties").filter(row -> row.g("objectid").eq(objectid)).run(rethinkDBAPI.getConnect(), Counties.class);
        if (run.hasNext()) {
            return run.next();
        } else {
            return null;
        }
    }
}
