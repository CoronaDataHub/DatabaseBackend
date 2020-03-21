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

package de.coronadatahub.databasebackend.config;

import com.google.gson.Gson;
import de.coronadatahub.databasebackend.config.models.Config;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
                writeConfig(new Config("localhost", "datahub", "passwort", "link", "apikey"));
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
