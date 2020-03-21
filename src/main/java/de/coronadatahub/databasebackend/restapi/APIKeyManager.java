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

public class APIKeyManager {

    private RethinkDBAPI rethinkDBAPI;

    public APIKeyManager(RethinkDBAPI rethinkDBAPI) {
        this.rethinkDBAPI = rethinkDBAPI;
    }




    public APIKey getKey(String objectid) {
        Result<APIKey> run = rethinkDBAPI.getR().db("Datahub").table("APIKeys").filter(row -> row.g("key").eq(objectid)).run(rethinkDBAPI.getConnect(), APIKey.class);
        if (run.hasNext()){
            return run.next();
        }else {
            return null;
        }
    }

    public void insert(APIKey apiKey){
        rethinkDBAPI.getR().db("Datahub").table("APIKeys").insert(apiKey).run(rethinkDBAPI.getConnect());
    }

    public boolean isKeyExist(String objectid){
        return getKey(objectid) != null;
    }

}
