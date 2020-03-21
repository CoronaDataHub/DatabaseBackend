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

public class GeometryProperties {
    private String shapeAreaFieldName;
    private String shapeLengthFieldName;
    private String units;

    public GeometryProperties() {
    }

    public GeometryProperties(String shapeAreaFieldName, String shapeLengthFieldName, String units) {
        this.shapeAreaFieldName = shapeAreaFieldName;
        this.shapeLengthFieldName = shapeLengthFieldName;
        this.units = units;
    }

    public String getShapeAreaFieldName() {
        return shapeAreaFieldName;
    }

    public void setShapeAreaFieldName(String shapeAreaFieldName) {
        this.shapeAreaFieldName = shapeAreaFieldName;
    }

    public String getShapeLengthFieldName() {
        return shapeLengthFieldName;
    }

    public void setShapeLengthFieldName(String shapeLengthFieldName) {
        this.shapeLengthFieldName = shapeLengthFieldName;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }
}
