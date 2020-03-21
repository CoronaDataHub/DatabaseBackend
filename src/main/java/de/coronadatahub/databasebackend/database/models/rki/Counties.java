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

package de.coronadatahub.databasebackend.database.models.rki;

import java.util.ArrayList;

public class Counties {


    private double OBJECTID;
    private String GEN;
    private String BEZ;
    private String BL;
    private double BL_ID;
    private String county;
    private ArrayList<CountiesData> countiesData;

    public Counties() {
    }

    public Counties(double OBJECTID, String GEN, String BEZ, String BL, double BL_ID, String county, ArrayList<CountiesData> countiesData) {
        this.OBJECTID = OBJECTID;
        this.GEN = GEN;
        this.BEZ = BEZ;
        this.BL = BL;
        this.BL_ID = BL_ID;
        this.county = county;
        this.countiesData = countiesData;
    }

    public double getOBJECTID() {
        return OBJECTID;
    }

    public void setOBJECTID(double OBJECTID) {
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

    public String getBL() {
        return BL;
    }

    public void setBL(String BL) {
        this.BL = BL;
    }

    public double getBL_ID() {
        return BL_ID;
    }

    public void setBL_ID(double BL_ID) {
        this.BL_ID = BL_ID;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public ArrayList<CountiesData> getCountiesData() {
        return countiesData;
    }

    public void setCountiesData(ArrayList<CountiesData> countiesData) {
        this.countiesData = countiesData;
    }
}
