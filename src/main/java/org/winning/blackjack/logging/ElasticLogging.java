package org.winning.blackjack.logging;

import org.winning.blackjack.repository.entity.PlayerActionsData;
import org.winning.blackjack.repository.entity.PlayerData;

public interface ElasticLogging {

    void loggingPlayerData(PlayerData data);

    void loggingPlayerActionData(PlayerActionsData data);
}
