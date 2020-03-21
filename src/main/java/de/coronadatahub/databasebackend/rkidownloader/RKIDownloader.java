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

package de.coronadatahub.databasebackend.rkidownloader;

import com.google.gson.Gson;
import de.coronadatahub.databasebackend.config.models.Config;
import de.coronadatahub.databasebackend.database.RethinkDBAPI;
import de.coronadatahub.databasebackend.rkidownloader.models.RKICounties;
import de.coronadatahub.databasebackend.services.JsonReaderService;

import java.io.IOException;

public class RKIDownloader implements Runnable {

    private JsonReaderService jsonReaderService;
    private Config config;
    private Gson gson;
    private RethinkDBAPI rethinkDBAPI;

    public RKIDownloader(Config config, Gson gson, RethinkDBAPI rethinkDBAPI) {
        this.config = config;
        this.rethinkDBAPI = rethinkDBAPI;
        this.gson = gson;
        jsonReaderService = new JsonReaderService();
    }

    @Override
    public void run() {
        try {
            uploadToRethinkDB(gson.fromJson(jsonReaderService.readJsonFromUrl(config.getRkiURL()), RKICounties.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void uploadToRethinkDB(RKICounties rkiCounties) {
        rethinkDBAPI.getTable(RethinkDBAPI.Tables.COUNTIES).insert(rkiCounties).run(rethinkDBAPI.getConnect());
    }
}
