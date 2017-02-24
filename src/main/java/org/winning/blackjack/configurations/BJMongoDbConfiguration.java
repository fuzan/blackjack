package org.winning.blackjack.configurations;

public class BJMongoDbConfiguration {
    private String connection;
    private int port;
    private String dbName;

    public BJMongoDbConfiguration() {
    }

    public BJMongoDbConfiguration(String connection, int port, String dbName) {
        this.connection = connection;
        this.port = port;
        this.dbName = dbName;
    }

    public String getConnection() {
        return connection;
    }

    public void setConnection(String connection) {
        this.connection = connection;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }
}
