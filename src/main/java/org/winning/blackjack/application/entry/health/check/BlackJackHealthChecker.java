package org.winning.blackjack.application.entry.health.check;

import com.google.inject.Inject;

import com.codahale.metrics.health.HealthCheck;

import org.winning.blackjack.configurations.BJMongoDbConfiguration;
import org.winning.blackjack.repository.mongo.connector.MongoDBDriver;

public class BlackJackHealthChecker extends HealthCheck {

    private MongoDBDriver dbDriver;

    @Inject
    public BlackJackHealthChecker(MongoDBDriver dbDriver) {
        this.dbDriver = dbDriver;
    }

    @Inject
    public BlackJackHealthChecker(BJMongoDbConfiguration configuration) {
        this.dbDriver =
                new MongoDBDriver(configuration.getConnection(), configuration.getPort(), configuration.getDbName());
    }

    @Override
    protected Result check() throws Exception {
        return dbDriver.isHealthy();
    }
}
