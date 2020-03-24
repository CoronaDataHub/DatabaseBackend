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

import com.rethinkdb.net.Result;
import de.coronadatahub.databasebackend.database.RethinkDBAPI;
import de.coronadatahub.databasebackend.database.models.apikey.APIKey;
import de.coronadatahub.databasebackend.database.models.apikey.APIKeyUsed;

public class APIKeyManager {

    private RethinkDBAPI rethinkDBAPI;

    public APIKeyManager(RethinkDBAPI rethinkDBAPI) {
        this.rethinkDBAPI = rethinkDBAPI;
    }


    public String isKeyValid(String apikey, String ip) {
        APIKey key = getKey(apikey);
        if (key != null) {
            if (key.getHostnames().contains(ip.split(":")[0])) {
                if (key.getRequestsAllTime() > key.getApiKeyUseds().size() || key.getRequestsAllTime() == -1){
                    APIKeyUsed apiKeyUsed = new APIKeyUsed(System.currentTimeMillis());
                    key.getApiKeyUseds().add(apiKeyUsed);
                    update(key);
                    return "true";
                }else {
                    return "Your all time requests are used up, please contact us for a new key.";
                }
            } else {
                return "This hostname is not registered, if you want to change the hostname please contact us.";
            }
        } else {
            return "The key is not registered!";
        }
    }


    public APIKey getKey(String key) {
        Result<APIKey> run = rethinkDBAPI.getR().db("Datahub").table("APIKeys").filter(row -> row.g("key").eq(key)).run(rethinkDBAPI.getConnect(), APIKey.class);
        if (run.hasNext()) {
            return run.next();
        } else {
            return null;
        }
    }

    private void deleteKey(String key){
        rethinkDBAPI.getR().db("Datahub").table("APIKeys").filter(row -> row.g("key").eq(key)).delete().run(rethinkDBAPI.getConnect());
    }

    public void insert(APIKey apiKey) {
        rethinkDBAPI.getR().db("Datahub").table("APIKeys").insert(apiKey).run(rethinkDBAPI.getConnect());
    }

    public void update(APIKey apiKey){
        deleteKey(apiKey.getKey());
        insert(apiKey);
    }


    @Deprecated
    public boolean isKeyExist(String objectid) {
        return getKey(objectid) != null;
    }

}
