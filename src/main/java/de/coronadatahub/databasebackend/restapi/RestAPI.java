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

import com.google.gson.Gson;
import de.coronadatahub.databasebackend.database.RethinkDBAPI;
import de.coronadatahub.databasebackend.database.models.apikey.APIKey;
import de.coronadatahub.databasebackend.database.models.coronavirusapp.Place;
import de.coronadatahub.databasebackend.database.models.rki.Counties;
import spark.Spark;

import java.util.List;

public class RestAPI {

    private APIKeyManager apiKeyManager;
    private RethinkDBAPI rethinkDBAPI;
    private Gson gson;

    public RestAPI(RethinkDBAPI rethinkDBAPI, Gson gson) {
        this.rethinkDBAPI = rethinkDBAPI;
        this.apiKeyManager = new APIKeyManager(rethinkDBAPI);
        this.gson = gson;
        init();
    }

    private void init() {
        Spark.before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));
        Spark.get("api/v1/coronavirusapp/getPlaces", (request, response) -> {
            String apikey = request.queryParams("apikey");
            System.out.println(request.host());
            if (apiKeyManager.isKeyExist(apikey)) {
                APIKey key = apiKeyManager.getKey(apikey);
                if (key.getHostnames().contains(request.host().split(":")[0])) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("[");
                    getPlaces().forEach(place -> {
                        stringBuilder.append(gson.toJson(place));
                        stringBuilder.append(",");
                    });
                    stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                    stringBuilder.append("]");
                    return stringBuilder.toString();
                } else {
                    return "This hostname is not registered, if you want to change the hostname please contact us.";
                }
            } else {
                return "The key is not registered!";
            }
        });

        Spark.get("/api/v1/rki/getCounties", (request, response) -> {
            String apikey = request.queryParams("apikey");
            if (apiKeyManager.isKeyExist(apikey)) {
                APIKey key = apiKeyManager.getKey(apikey);
                if (key.getHostnames().contains(request.host().split(":")[0])) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("[");
                    getCounties().forEach(place -> {
                        stringBuilder.append(gson.toJson(place));
                        stringBuilder.append(",");
                    });
                    stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                    stringBuilder.append("]");
                    System.out.println(stringBuilder.toString());
                    return stringBuilder.toString();
                } else {
                    return "This hostname is not registered, if you want to change the hostname please contact us.";
                }
            } else {
                return "The key is not registered!";
            }
        });
    }

    private List<Place> getPlaces() {
        return rethinkDBAPI.getR().db("Datahub").table("Coronavirusapp").run(rethinkDBAPI.getConnect(), Place.class).toList();
    }

    private List<Counties> getCounties() {
        return rethinkDBAPI.getR().db("Datahub").table("Counties").run(rethinkDBAPI.getConnect(), Counties.class).toList();
    }




}
