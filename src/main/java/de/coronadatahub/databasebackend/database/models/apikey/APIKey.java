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

package de.coronadatahub.databasebackend.database.models.apikey;

import java.util.ArrayList;

public class APIKey {

    private int objectID;
    private long createTime;
    private String key;
    private boolean activated;
    private String owner;
    private String ownerEmail;
    private ArrayList<String> hostnames;
    private boolean admin;

    public APIKey() {
    }

    public APIKey(int objectID, long createTime, String key, boolean activated, String owner, String ownerEmail, ArrayList<String> hostnames, boolean admin) {
        this.objectID = objectID;
        this.createTime = createTime;
        this.key = key;
        this.activated = activated;
        this.owner = owner;
        this.ownerEmail = ownerEmail;
        this.hostnames = hostnames;
        this.admin = admin;
    }

    public int getObjectID() {
        return objectID;
    }

    public void setObjectID(int objectID) {
        this.objectID = objectID;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public ArrayList<String> getHostnames() {
        return hostnames;
    }

    public void setHostnames(ArrayList<String> hostnames) {
        this.hostnames = hostnames;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
