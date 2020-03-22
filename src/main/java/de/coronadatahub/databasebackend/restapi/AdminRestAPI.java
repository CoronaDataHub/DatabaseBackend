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
import spark.Spark;

public class AdminRestAPI {
    private APIKeyManager apiKeyManager;
    private RethinkDBAPI rethinkDBAPI;
    private Gson gson;

    public AdminRestAPI(RethinkDBAPI rethinkDBAPI, Gson gson) {
        this.rethinkDBAPI = rethinkDBAPI;
        this.apiKeyManager = new APIKeyManager(rethinkDBAPI);
        this.gson = gson;
        init();
    }

    private void init(){
        Spark.post("/api/v1/apikey/createKey", (request, response) -> {
            String apikey = request.queryParams("apikey");
            if (apiKeyManager.isKeyExist(apikey)){
                APIKey key = apiKeyManager.getKey(apikey);
                if (key.getHostnames().contains(request.host().split(":")[0])) {
                    try {
                        APIKey apiKey = gson.fromJson(request.body(), APIKey.class);
                        apiKeyManager.insert(apiKey);
                        return "success";
                    }catch (Exception ex){
                        ex.printStackTrace();
                        return "Body is wrong!";
                    }
                }else {
                    return "This hostname is not registered, if you want to change the hostname please contact us.";
                }
            }else {
                return "The key is not registered!";
            }
        });
    }


}
