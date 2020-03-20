package de.coronadatahub.databasebackend;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rethinkdb.RethinkDB;
import com.rethinkdb.net.Connection;
import de.coronadatahub.databasebackend.config.ConfigManager;
import de.coronadatahub.databasebackend.config.models.Config;
import de.coronadatahub.databasebackend.database.RethinkDBAPI;
import de.coronadatahub.databasebackend.timer.TimerTask;

public class DataBaseBackend {

    private RethinkDB rethinkDB = RethinkDB.r;
    private Connection connection;
    private RethinkDBAPI rethinkDBAPI;

    private Gson gson;
    private ConfigManager configManager;

    private TimerTask timerTask;

    public DataBaseBackend() {
        this.gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                .setPrettyPrinting().create();
        this.configManager = new ConfigManager(gson);
        setupRethinkDB();

        timerTask = new TimerTask();
    }

    private void setupRethinkDB() {
        Config config = this.configManager.readConfig();
        this.connection = this.rethinkDB.connection().hostname(config.getHostname()).user(config.getUsername(), config.getPasswort()).connect();
        rethinkDBAPI = new RethinkDBAPI(rethinkDB, connection);
    }
}
