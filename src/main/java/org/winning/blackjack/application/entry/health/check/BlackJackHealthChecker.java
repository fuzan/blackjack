package org.winning.blackjack.application.entry.health.check;

import com.google.inject.Inject;

import com.codahale.metrics.health.HealthCheck;

import org.winning.blackjack.repository.mongo.connector.MongoDBDriver;

public class BlackJackHealthChecker extends HealthCheck {

    private MongoDBDriver dbDriver;

    @Inject
    public BlackJackHealthChecker(MongoDBDriver dbDriver) {
        this.dbDriver = dbDriver;
    }

    @Override
    protected Result check() throws Exception {
        return dbDriver.isHealthy();
    }
}
