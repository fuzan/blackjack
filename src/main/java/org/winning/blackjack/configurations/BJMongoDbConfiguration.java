package org.winning.blackjack.configurations;

public class BJMongoDbConfiguration {
    private String connection;
    private int port;
    private String dbName;

    public BJMongoDbConfiguration(String connection, int port, String dbName) {
        this.connection = connection;
        this.port = port;
        this.dbName = dbName;
    }

    public void setConnection(String connection) {
        this.connection = connection;
    }

    public String getConnection() {
        return connection;
    }

    public int getPort() {
        return port;
    }

    public String getDbName() {
        return dbName;
    }

}
