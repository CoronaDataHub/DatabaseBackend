package de.coronadatahub.databasebackend.database;

import com.rethinkdb.RethinkDB;
import com.rethinkdb.net.Connection;

public class RethinkDBAPI {

    private RethinkDB rethinkDB;
    private Connection connection;

    public RethinkDBAPI(RethinkDB rethinkDB, Connection connection) {
        this.rethinkDB = rethinkDB;
        this.connection = connection;
    }

    public RethinkDB getR() {
        return rethinkDB;
    }
    public Connection getConnect() {
        return connection;
    }

}
