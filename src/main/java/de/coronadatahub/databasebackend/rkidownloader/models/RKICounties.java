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

import java.util.ArrayList;

public class RKICounties {
    private String objectIdFieldName;
    private UniqueIdField UniqueIdFieldObject;
    private String globalIdFieldName;
    private GeometryProperties GeometryPropertiesObject;
    private String geometryType;
    private SpatialReference SpatialReferenceObject;
    private ArrayList<Fields> fields = new ArrayList<>();
    private ArrayList<Features> features = new ArrayList<>();

    public RKICounties() {
    }

    public RKICounties(String objectIdFieldName, UniqueIdField uniqueIdFieldObject, String globalIdFieldName, GeometryProperties geometryPropertiesObject, String geometryType, SpatialReference spatialReferenceObject, ArrayList<Fields> fields, ArrayList<Features> features) {
        this.objectIdFieldName = objectIdFieldName;
        UniqueIdFieldObject = uniqueIdFieldObject;
        this.globalIdFieldName = globalIdFieldName;
        GeometryPropertiesObject = geometryPropertiesObject;
        this.geometryType = geometryType;
        SpatialReferenceObject = spatialReferenceObject;
        this.fields = fields;
        this.features = features;
    }

    public String getObjectIdFieldName() {
        return objectIdFieldName;
    }

    public void setObjectIdFieldName(String objectIdFieldName) {
        this.objectIdFieldName = objectIdFieldName;
    }

    public UniqueIdField getUniqueIdFieldObject() {
        return UniqueIdFieldObject;
    }

    public void setUniqueIdFieldObject(UniqueIdField uniqueIdFieldObject) {
        UniqueIdFieldObject = uniqueIdFieldObject;
    }

    public String getGlobalIdFieldName() {
        return globalIdFieldName;
    }

    public void setGlobalIdFieldName(String globalIdFieldName) {
        this.globalIdFieldName = globalIdFieldName;
    }

    public GeometryProperties getGeometryPropertiesObject() {
        return GeometryPropertiesObject;
    }

    public void setGeometryPropertiesObject(GeometryProperties geometryPropertiesObject) {
        GeometryPropertiesObject = geometryPropertiesObject;
    }

    public String getGeometryType() {
        return geometryType;
    }

    public void setGeometryType(String geometryType) {
        this.geometryType = geometryType;
    }

    public SpatialReference getSpatialReferenceObject() {
        return SpatialReferenceObject;
    }

    public void setSpatialReferenceObject(SpatialReference spatialReferenceObject) {
        SpatialReferenceObject = spatialReferenceObject;
    }

    public ArrayList<Fields> getFields() {
        return fields;
    }

    public void setFields(ArrayList<Fields> fields) {
        this.fields = fields;
    }

    public ArrayList<Features> getFeatures() {
        return features;
    }

    public void setFeatures(ArrayList<Features> features) {
        this.features = features;
    }
}
