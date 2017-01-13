package org.winning.blackjack.repository;

import org.winning.blackjack.repository.entity.PlayerActionsData;
import org.winning.blackjack.repository.entity.PlayerData;

import java.util.List;

public interface GamesI {

    List<PlayerData> getAllPlayerData() throws Exception;

    void savePlayerData(PlayerData data) throws Exception;

    void savePlayerActionData(PlayerActionsData data) throws Exception;
}
