package org.winning.blackjack.game.statistic.service;

import org.winning.blackjack.entity.Card;
import org.winning.blackjack.people.Player;
import org.winning.blackjack.repository.entity.PlayerActionsData;
import org.winning.blackjack.repository.entity.PlayerData;

import java.util.List;

public class GameStatisticMongoDBImp implements GameStatistic {

    @Override
    public int getPlayerGameWin(Player player) {
        return 0;
    }

    @Override
    public int getPlayerGameLost(Player player) {
        return 0;
    }

    @Override
    public int getPlayerBlackJackHands(Player player) {
        return 0;
    }

    @Override
    public int getPlayerDoubleHands(Player player) {
        return 0;
    }

    @Override
    public int getPlayerSplitHands(Player player) {
        return 0;
    }

    @Override
    public List<Card>[] getPlayerHands(Player player, int gameId, int roundId) {
        return null;
    }

    @Override
    public void savePlayerData(PlayerData playerData) {

    }

    @Override
    public void savePlayerAction(PlayerActionsData playerData){

    }
}
