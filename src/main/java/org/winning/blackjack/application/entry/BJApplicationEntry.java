package org.winning.blackjack.application.entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.winning.blackjack.configurations.BJConfiguration;
import org.winning.blackjack.controller.GameController;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class BJApplicationEntry extends Application<BJConfiguration>{

    final private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public String getName() {
        return "BlackJack game started !";
    }

    @Override
    public void initialize(Bootstrap<BJConfiguration> bootstrap) {
        super.initialize(bootstrap);
    }

    @Override
    public void run(BJConfiguration configuration, Environment environment) throws Exception {
        logger.info(environment.getName());
        final GameController controller = new GameController(configuration);
        environment.jersey().register(controller);
    }
}
