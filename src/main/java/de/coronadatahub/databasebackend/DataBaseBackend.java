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
package de.coronadatahub.databasebackend;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rethinkdb.RethinkDB;
import com.rethinkdb.net.Connection;
import de.coronadatahub.databasebackend.config.ConfigManager;
import de.coronadatahub.databasebackend.config.models.Config;
import de.coronadatahub.databasebackend.coronavirusappdownloader.CoronavirusappDownloader;
import de.coronadatahub.databasebackend.database.RethinkDBAPI;
import de.coronadatahub.databasebackend.rkidownloader.RKIDownloader;
import de.coronadatahub.databasebackend.timer.TimerTask;
import de.coronadatahub.databasebackend.timer.model.Time;

public class DataBaseBackend {

    private RethinkDB rethinkDB = RethinkDB.r;
    private Connection connection;
    private RethinkDBAPI rethinkDBAPI;

    private Gson gson;
    private ConfigManager configManager;
    private Config config;

    private TimerTask timerTask;

    public DataBaseBackend() {
        this.gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                .setPrettyPrinting().create();
        this.configManager = new ConfigManager(gson);
        setupRethinkDB();
        timerTask = new TimerTask();

        registerRunnable();
    }

    private void setupRethinkDB() {
        config = this.configManager.readConfig();
        this.connection = this.rethinkDB.connection().hostname(config.getHostname()).user(config.getUsername(), config.getPasswort()).connect();
        rethinkDBAPI = new RethinkDBAPI(rethinkDB, connection);
    }

    private void registerRunnable(){
        Runnable coronavirusappDownloader = new CoronavirusappDownloader(config, gson, rethinkDBAPI);
        timerTask.addTask(Time.t(0,0), coronavirusappDownloader);
        timerTask.addTask(Time.t(2,0), coronavirusappDownloader);
        timerTask.addTask(Time.t(4,0), coronavirusappDownloader);
        timerTask.addTask(Time.t(6,0), coronavirusappDownloader);
        timerTask.addTask(Time.t(8,0), coronavirusappDownloader);
        timerTask.addTask(Time.t(10,0), coronavirusappDownloader);
        timerTask.addTask(Time.t(12,0), coronavirusappDownloader);
        timerTask.addTask(Time.t(14,0), coronavirusappDownloader);
        timerTask.addTask(Time.t(16,0), coronavirusappDownloader);
        timerTask.addTask(Time.t(18,0), coronavirusappDownloader);
        timerTask.addTask(Time.t(20,0), coronavirusappDownloader);
        timerTask.addTask(Time.t(22,0), coronavirusappDownloader);


        Runnable rkiDownloader = new RKIDownloader(config, gson, rethinkDBAPI);
        timerTask.addTask(Time.t(0, 0), rkiDownloader);
        timerTask.addTask(Time.t(12,0), rkiDownloader);
    }

    public RethinkDB getRethinkDB() {
        return rethinkDB;
    }

    public Connection getConnection() {
        return connection;
    }

    public RethinkDBAPI getRethinkDBAPI() {
        return rethinkDBAPI;
    }

    public Gson getGson() {
        return gson;
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public TimerTask getTimerTask() {
        return timerTask;
    }

    public Config getConfig() {
        return config;
    }

}
