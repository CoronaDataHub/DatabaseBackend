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

package de.coronadatahub.databasebackend.restapi;

import de.coronadatahub.databasebackend.database.RethinkDBAPI;
import de.coronadatahub.databasebackend.database.models.apikey.APIKey;
import de.coronadatahub.databasebackend.database.models.coronavirusapp.Place;
import de.coronadatahub.databasebackend.rkidownloader.models.RKICounties;
import spark.Spark;

import java.util.List;

public class RestAPI {

    private APIKeyManager apiKeyManager;
    private RethinkDBAPI rethinkDBAPI;

    public RestAPI(RethinkDBAPI rethinkDBAPI) {
        this.rethinkDBAPI = rethinkDBAPI;
        this.apiKeyManager = new APIKeyManager(rethinkDBAPI);
        init();
    }

    private void init(){
        Spark.get("/api/v1/coronavirusapp/getPlaces", (request, response) -> {
            String apikey = request.headers("APIKEY");
            if (apiKeyManager.isKeyExist(apikey)){
                APIKey key = apiKeyManager.getKey(apikey);
                if (key.getHostnames().contains(request.host().split(":")[0])) {
                    StringBuilder stringBuilder = new StringBuilder();
                    getPlaces().forEach(stringBuilder::append);
                    return stringBuilder.toString();
                }else {
                    return "This hostname is not registered, if you want to change the hostname please contact us.";
                }
            }else {
                return "The key is not registered!";
            }
        });

        Spark.get("/api/v1/rki/getCounties", (request, response) -> {
            String apikey = request.headers("APIKEY");
            if (apiKeyManager.isKeyExist(apikey)){
                APIKey key = apiKeyManager.getKey(apikey);
                if (key.getHostnames().contains(request.host().split(":")[0])) {
                    StringBuilder stringBuilder = new StringBuilder();
                    getCounties().forEach(stringBuilder::append);
                    return stringBuilder.toString();
                }else {
                    return "This hostname is not registered, if you want to change the hostname please contact us.";
                }
            }else {
                return "The key is not registered!";
            }
        });
    }

    private List<Place> getPlaces() {
        return rethinkDBAPI.getR().db("Datahub").table("Coronavirusapp").run(rethinkDBAPI.getConnect(), Place.class).toList();
    }

    private List<RKICounties> getCounties() {
        return rethinkDBAPI.getR().db("Datahub").table("Counties").run(rethinkDBAPI.getConnect(), RKICounties.class).toList();
    }


}
