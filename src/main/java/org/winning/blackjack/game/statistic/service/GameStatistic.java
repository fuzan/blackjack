package org.winning.blackjack.game.statistic.service;

import org.winning.blackjack.entity.Card;
import org.winning.blackjack.people.Player;
import org.winning.blackjack.repository.entity.PlayerActionsData;
import org.winning.blackjack.repository.entity.PlayerData;

import java.util.List;

public interface GameStatistic {

    int getPlayerGameWin(Player player);

    int getPlayerGameLost(Player player);

    int getPlayerBlackJackHands(Player player);

    int getPlayerDoubleHands(Player player);

    int getPlayerSplitHands(Player player);

    List<Card>[] getPlayerHands(Player player, int gameId, int roundId);

    void savePlayerData(PlayerData playerData);

    void savePlayerAction(PlayerActionsData playerData);
}
