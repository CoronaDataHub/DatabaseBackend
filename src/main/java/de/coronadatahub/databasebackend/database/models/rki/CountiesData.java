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

public class CountiesData {

    private long time;

    private float death_rate;
    private float cases;
    private float deaths;
    private float cases_per_100k;
    private float cases_per_population;

    public CountiesData() {
    }

    public CountiesData(long time, float death_rate, float cases, float deaths, float cases_per_100k, float cases_per_population) {
        this.time = time;
        this.death_rate = death_rate;
        this.cases = cases;
        this.deaths = deaths;
        this.cases_per_100k = cases_per_100k;
        this.cases_per_population = cases_per_population;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
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
}
