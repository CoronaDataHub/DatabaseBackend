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

package de.coronadatahub.databasebackend.rkidownloader.models;

public class Attributes {
    private float OBJECTID;
    private float ADE;
    private float GF;
    private float BSG;
    private String RS;
    private String AGS;
    private String SDV_RS;
    private String GEN;
    private String BEZ;
    private float IBZ;
    private String BEM;
    private String NBD;
    private String SN_L;
    private String SN_R;
    private String SN_K;
    private String SN_V1;
    private String SN_V2;
    private String SN_G;
    private String FK_S3;
    private String NUTS;
    private String RS_0;
    private String AGS_0;
    private String WSK;
    private float EWZ;
    private float KFL;
    private String DEBKG_ID;
    private float Shape__Area;
    private float Shape__Length;
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

    public Attributes(float OBJECTID, float ADE, float GF, float BSG, String RS, String AGS, String SDV_RS, String GEN, String BEZ, float IBZ, String BEM, String NBD, String SN_L, String SN_R, String SN_K, String SN_V1, String SN_V2, String SN_G, String FK_S3, String NUTS, String RS_0, String AGS_0, String WSK, float EWZ, float KFL, String DEBKG_ID, float shape__Area, float shape__Length, float death_rate, float cases, float deaths, float cases_per_100k, float cases_per_population, String BL, String BL_ID, String county) {
        this.OBJECTID = OBJECTID;
        this.ADE = ADE;
        this.GF = GF;
        this.BSG = BSG;
        this.RS = RS;
        this.AGS = AGS;
        this.SDV_RS = SDV_RS;
        this.GEN = GEN;
        this.BEZ = BEZ;
        this.IBZ = IBZ;
        this.BEM = BEM;
        this.NBD = NBD;
        this.SN_L = SN_L;
        this.SN_R = SN_R;
        this.SN_K = SN_K;
        this.SN_V1 = SN_V1;
        this.SN_V2 = SN_V2;
        this.SN_G = SN_G;
        this.FK_S3 = FK_S3;
        this.NUTS = NUTS;
        this.RS_0 = RS_0;
        this.AGS_0 = AGS_0;
        this.WSK = WSK;
        this.EWZ = EWZ;
        this.KFL = KFL;
        this.DEBKG_ID = DEBKG_ID;
        Shape__Area = shape__Area;
        Shape__Length = shape__Length;
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

    public float getADE() {
        return ADE;
    }

    public void setADE(float ADE) {
        this.ADE = ADE;
    }

    public float getGF() {
        return GF;
    }

    public void setGF(float GF) {
        this.GF = GF;
    }

    public float getBSG() {
        return BSG;
    }

    public void setBSG(float BSG) {
        this.BSG = BSG;
    }

    public String getRS() {
        return RS;
    }

    public void setRS(String RS) {
        this.RS = RS;
    }

    public String getAGS() {
        return AGS;
    }

    public void setAGS(String AGS) {
        this.AGS = AGS;
    }

    public String getSDV_RS() {
        return SDV_RS;
    }

    public void setSDV_RS(String SDV_RS) {
        this.SDV_RS = SDV_RS;
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

    public float getIBZ() {
        return IBZ;
    }

    public void setIBZ(float IBZ) {
        this.IBZ = IBZ;
    }

    public String getBEM() {
        return BEM;
    }

    public void setBEM(String BEM) {
        this.BEM = BEM;
    }

    public String getNBD() {
        return NBD;
    }

    public void setNBD(String NBD) {
        this.NBD = NBD;
    }

    public String getSN_L() {
        return SN_L;
    }

    public void setSN_L(String SN_L) {
        this.SN_L = SN_L;
    }

    public String getSN_R() {
        return SN_R;
    }

    public void setSN_R(String SN_R) {
        this.SN_R = SN_R;
    }

    public String getSN_K() {
        return SN_K;
    }

    public void setSN_K(String SN_K) {
        this.SN_K = SN_K;
    }

    public String getSN_V1() {
        return SN_V1;
    }

    public void setSN_V1(String SN_V1) {
        this.SN_V1 = SN_V1;
    }

    public String getSN_V2() {
        return SN_V2;
    }

    public void setSN_V2(String SN_V2) {
        this.SN_V2 = SN_V2;
    }

    public String getSN_G() {
        return SN_G;
    }

    public void setSN_G(String SN_G) {
        this.SN_G = SN_G;
    }

    public String getFK_S3() {
        return FK_S3;
    }

    public void setFK_S3(String FK_S3) {
        this.FK_S3 = FK_S3;
    }

    public String getNUTS() {
        return NUTS;
    }

    public void setNUTS(String NUTS) {
        this.NUTS = NUTS;
    }

    public String getRS_0() {
        return RS_0;
    }

    public void setRS_0(String RS_0) {
        this.RS_0 = RS_0;
    }

    public String getAGS_0() {
        return AGS_0;
    }

    public void setAGS_0(String AGS_0) {
        this.AGS_0 = AGS_0;
    }

    public String getWSK() {
        return WSK;
    }

    public void setWSK(String WSK) {
        this.WSK = WSK;
    }

    public float getEWZ() {
        return EWZ;
    }

    public void setEWZ(float EWZ) {
        this.EWZ = EWZ;
    }

    public float getKFL() {
        return KFL;
    }

    public void setKFL(float KFL) {
        this.KFL = KFL;
    }

    public String getDEBKG_ID() {
        return DEBKG_ID;
    }

    public void setDEBKG_ID(String DEBKG_ID) {
        this.DEBKG_ID = DEBKG_ID;
    }

    public float getShape__Area() {
        return Shape__Area;
    }

    public void setShape__Area(float shape__Area) {
        Shape__Area = shape__Area;
    }

    public float getShape__Length() {
        return Shape__Length;
    }

    public void setShape__Length(float shape__Length) {
        Shape__Length = shape__Length;
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
