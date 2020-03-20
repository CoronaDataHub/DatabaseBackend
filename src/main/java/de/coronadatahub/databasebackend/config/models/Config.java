package de.coronadatahub.databasebackend.config.models;

public class Config {

    private String hostname;
    private String username;
    private String passwort;

    public Config() {
    }

    public Config(String hostname, String username, String passwort) {
        this.hostname = hostname;
        this.username = username;
        this.passwort = passwort;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }
}
