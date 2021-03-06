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

package de.coronadatahub.databasebackend.rkidownloader.models;

public class Attributes {
    private float OBJECTID;
    private String GEN;
    private String BEZ;
    private float death_rate;
    private float cases;
    private float deaths;
    private float cases_per_100k;
    private float cases_per_population;
    private String BL;
    private String BL_ID;
    private String county;


    public Attributes() {
    }

    public Attributes(float OBJECTID, String GEN, String BEZ, float death_rate, float cases, float deaths, float cases_per_100k, float cases_per_population, String BL, String BL_ID, String county) {
        this.OBJECTID = OBJECTID;
        this.GEN = GEN;
        this.BEZ = BEZ;
        this.death_rate = death_rate;
        this.cases = cases;
        this.deaths = deaths;
        this.cases_per_100k = cases_per_100k;
        this.cases_per_population = cases_per_population;
        this.BL = BL;
        this.BL_ID = BL_ID;
        this.county = county;
    }

    public float getOBJECTID() {
        return OBJECTID;
    }

    public void setOBJECTID(float OBJECTID) {
        this.OBJECTID = OBJECTID;
    }

    public String getGEN() {
        return GEN;
    }

    public void setGEN(String GEN) {
        this.GEN = GEN;
    }

    public String getBEZ() {
        return BEZ;
    }

    public void setBEZ(String BEZ) {
        this.BEZ = BEZ;
    }

    public float getDeath_rate() {
        return death_rate;
    }

    public void setDeath_rate(float death_rate) {
        this.death_rate = death_rate;
    }

    public float getCases() {
        return cases;
    }

    public void setCases(float cases) {
        this.cases = cases;
    }

    public float getDeaths() {
        return deaths;
    }

    public void setDeaths(float deaths) {
        this.deaths = deaths;
    }

    public float getCases_per_100k() {
        return cases_per_100k;
    }

    public void setCases_per_100k(float cases_per_100k) {
        this.cases_per_100k = cases_per_100k;
    }

    public float getCases_per_population() {
        return cases_per_population;
    }

    public void setCases_per_population(float cases_per_population) {
        this.cases_per_population = cases_per_population;
    }

    public String getBL() {
        return BL;
    }

    public void setBL(String BL) {
        this.BL = BL;
    }

    public String getBL_ID() {
        return BL_ID;
    }

    public void setBL_ID(String BL_ID) {
        this.BL_ID = BL_ID;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }
}
