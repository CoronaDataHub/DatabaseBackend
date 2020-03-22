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

public class DataHistory {

    private long time;
    private double infected;
    private double recovered;
    private double dead;
    private double sick;
    private double darkFigure;

    public DataHistory() {
    }

    public DataHistory(long time, double infected, double recovered, double dead, double sick, double darkFigure) {
        this.time = time;
        this.infected = infected;
        this.recovered = recovered;
        this.dead = dead;
        this.sick = sick;
        this.darkFigure = darkFigure;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public double getInfected() {
        return infected;
    }

    public void setInfected(double infected) {
        this.infected = infected;
    }

    public double getRecovered() {
        return recovered;
    }

    public void setRecovered(double recovered) {
        this.recovered = recovered;
    }

    public double getDead() {
        return dead;
    }

    public void setDead(double dead) {
        this.dead = dead;
    }

    public double getSick() {
        return sick;
    }

    public void setSick(double sick) {
        this.sick = sick;
    }

    public double getDarkFigure() {
        return darkFigure;
    }

    public void setDarkFigure(double darkFigure) {
        this.darkFigure = darkFigure;
    }
}
