package org.winning.blackjack.application.entry;

import org.winning.blackjack.configurations.BJConfiguration;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class BJApplicationEntry extends Application<BJConfiguration>{

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void initialize(Bootstrap<BJConfiguration> bootstrap) {
        super.initialize(bootstrap);
    }

    @Override
    public void run(BJConfiguration configuration, Environment environment) throws Exception {

    }
}
