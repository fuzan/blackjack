package org.winning.blackjack.repository.entity;

import org.winning.blackjack.entity.Action;

import java.util.List;

public class PlayerActionsData {

    private int gameId;
    private int handsId;
    private List<Action> playerActions;

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getHandsId() {
        return handsId;
    }

    public void setHandsId(int handsId) {
        this.handsId = handsId;
    }

    public List<Action> getPlayerActions() {
        return playerActions;
    }

    public void setPlayerActions(List<Action> playerActions) {
        this.playerActions = playerActions;
    }

}
