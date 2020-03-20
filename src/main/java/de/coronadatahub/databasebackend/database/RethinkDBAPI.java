package de.coronadatahub.databasebackend.database;

import com.rethinkdb.RethinkDB;
import com.rethinkdb.gen.ast.Db;
import com.rethinkdb.gen.ast.Table;
import com.rethinkdb.net.Connection;

public class RethinkDBAPI {

    private RethinkDB rethinkDB;
    private Connection connection;
    private Db dataHubDB;

    public RethinkDBAPI(RethinkDB rethinkDB, Connection connection) {
        this.rethinkDB = rethinkDB;
        this.connection = connection;
        dataHubDB = rethinkDB.db("Datahub");
        init();
    }

    private void init() {
        for (Tables tables : Tables.values()) {
            createTableIfNotExist(dataHubDB, tables.getTable());
        }
    }

    private void createTableIfNotExist(Db dataHubDB, String table) {
        if (!dataHubDB.tableList().contains(table).run(connection, Boolean.class).first()) {
            dataHubDB.tableCreate(table).run(connection);
        }
    }

    public Table getTable(Tables tables) {
        return rethinkDB.db(dataHubDB).table(tables.table);
    }

    public Table getTableByName(String database, String table) {
        return rethinkDB.db(database).table(table);
    }

    public RethinkDB getR() {
        return rethinkDB;
    }

    public Connection getConnect() {
        return connection;
    }

    public Db getDataHubDB() {
        return dataHubDB;
    }

    public enum Tables {

        COUNTIES("Counties");

        private String table;

        Tables(String table) {
            this.table = table;
        }

        public String getTable() {
            return table;
        }
    }
}
