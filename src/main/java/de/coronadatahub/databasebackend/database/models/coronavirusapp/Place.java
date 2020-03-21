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

package de.coronadatahub.databasebackend.database.models.coronavirusapp;

import java.util.ArrayList;

public class Place {

    private String objectid;
    private String name;
    private String country;
    private String longitude;
    private String latitude;
    private String lastUpdated;
    private ArrayList<DataHistory> dataHistories;

    public Place() {
    }

    public Place(String objectid, String name, String country, String longitude, String latitude, String lastUpdated, String infected, String recovered, String dead, String sick, ArrayList<DataHistory> dataHistories) {
        this.objectid = objectid;
        this.name = name;
        this.country = country;
        this.longitude = longitude;
        this.latitude = latitude;
        this.lastUpdated = lastUpdated;
        this.dataHistories = dataHistories;
    }

    public String getObjectid() {
        return objectid;
    }

    public void setObjectid(String id) {
        this.objectid = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public ArrayList<DataHistory> getDataHistories() {
        return dataHistories;
    }

    public void setDataHistories(ArrayList<DataHistory> dataHistories) {
        this.dataHistories = dataHistories;
    }
}
