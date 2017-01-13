package org.winning.blackjack.repository.mongoConnector;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Optional;

public class MongoDBDriver {

    private MongoClient mongoClient;
    private DB db;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private String dbName;

    public MongoDBDriver(String ips, int port, String dbName) {
        try {
            this.dbName = dbName;
            this.mongoClient = new MongoClient(ips, port);
            this.mongoClient.setWriteConcern(WriteConcern.JOURNALED);
        } catch (UnknownHostException e) {
            logger.error(e.getMessage(), e);
        }
    }

    private Optional<DB> getMongoDB() {
        this.db = mongoClient.getDB(dbName);
        return Optional.of(this.db);
    }

    public DBCollection getCollection(String collectionName) throws Exception {
        if (this.getMongoDB().isPresent()) {
            return this.getMongoDB().get().getCollection(collectionName);
        }
        throw new Exception("MongoDB " + dbName + " is not exist !");
    }


    /**
     * MongoDB does not need to close any connections
     */
    public void closeResources() {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }

    public Optional<List<String>> getAllMongoDBs() {
        if (mongoClient != null) {
            return Optional.of(mongoClient.getDatabaseNames());
        }
        return Optional.empty();
    }
}
