package org.winning.blackjack.repository;

import org.winning.blackjack.repository.entity.PlayerActionsData;
import org.winning.blackjack.repository.entity.PlayerData;

import java.util.List;

public interface GamesI {

    List<PlayerData> getAllPlayerData();

    void savePlayerData(PlayerData data);

    void savePlayerActionData(PlayerActionsData data);

    void deletePlayerData(PlayerData data);
}
