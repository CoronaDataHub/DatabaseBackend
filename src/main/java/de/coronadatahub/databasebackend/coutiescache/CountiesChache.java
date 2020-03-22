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

package de.coronadatahub.databasebackend.coutiescache;

import com.google.gson.Gson;
import de.coronadatahub.databasebackend.coutiescache.models.SearchList;
import de.coronadatahub.databasebackend.services.JsonReaderService;

import java.io.IOException;

public class CountiesChache {

    private JsonReaderService jsonReaderService;
    private Gson gson;

    public CountiesChache(Gson gson) {
        this.gson = gson;
        jsonReaderService = new JsonReaderService();
    }

    public SearchList getContent(String key) {
        try {

            String json = "{\"searches\": "+jsonReaderService.readJsonFromUrl("https://nominatim.openstreetmap.org/search?q=" + key + "&format=json") + "}";
            return gson.fromJson(json, SearchList.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
