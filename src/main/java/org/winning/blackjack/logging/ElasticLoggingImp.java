package org.winning.blackjack.logging;

import com.google.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.winning.blackjack.repository.entity.PlayerActionsData;
import org.winning.blackjack.repository.entity.PlayerData;

import java.io.IOException;

public class ElasticLoggingImp implements ElasticLogging {

    private Logger LOGGER  = LoggerFactory.getLogger(this.getClass());

    private ElasticClient client;

    @Inject
    public ElasticLoggingImp(ElasticClient client) {
        this.client = client;
    }

    @Override
    public void loggingPlayerData(PlayerData data) {
        final boolean succ;
        try {
            succ = client.saveDocument(data);
            LOGGER.info("document save {} ", succ);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public void loggingPlayerActionData(PlayerActionsData data) {

    }
}
