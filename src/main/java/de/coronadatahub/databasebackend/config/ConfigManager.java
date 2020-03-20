package de.coronadatahub.databasebackend.config;

import com.google.gson.Gson;
import de.coronadatahub.databasebackend.config.models.Config;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ConfigManager {

    private Gson gson;
    private Path configFolder;
    private Path configFile;

    public ConfigManager(Gson gson) {
        this.gson = gson;
        configFolder = Paths.get("config");
        configFile = Paths.get("config", "datahub.dataconfig");
        setup();
    }

    private void setup() {
        if (!Files.exists(configFolder)) {
            try {
                Files.createDirectories(configFolder);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (!Files.exists(configFile)) {
            try {
                Files.createFile(configFile);
                writeConfig(new Config("localhost", "datahub", "passwort"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Config readConfig() {
        try {
            return gson.fromJson(new FileReader(configFile.toFile()), Config.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void writeConfig(Config config) {
        try {
            gson.toJson(config, new FileWriter(configFile.toFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
