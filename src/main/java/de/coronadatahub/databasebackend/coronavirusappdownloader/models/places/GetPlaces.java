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
package de.coronadatahub.databasebackend.coronavirusappdownloader.models.places;

import java.util.ArrayList;

public class GetPlaces {

    private ArrayList<PlacesData> data;

    public GetPlaces() {
    }

    public GetPlaces(ArrayList<PlacesData> data) {
        this.data = data;
    }

    public ArrayList<PlacesData> getData() {
        return data;
    }

    public void setData(ArrayList<PlacesData> data) {
        this.data = data;
    }
}
