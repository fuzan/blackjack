package org.winning.blackjack.people;

import org.winning.blackjack.entity.CardSum;
import org.winning.blackjack.gamblingactions.PlayerDealerCommonAction;

import java.util.LinkedList;

public class Player extends BaseUser {

    private int stake;
    private int betting;
    private boolean splitted;
    private Player parentPlayer;
    private Player[] twoSplitedPlayer = new Player[2];
    private boolean inGame;
    private PlayerDealerCommonAction playerAction;

    public Player(String name) {
        super(name);
    }

    public Player[] getTwoSplitedPlayer() {
        return twoSplitedPlayer;
    }

    public void setTwoSplitedPlayer(Player[] twoSplitedPlayer) {
        this.twoSplitedPlayer = twoSplitedPlayer;
    }

    public Player getParentPlayer() {
        return parentPlayer;
    }

    public void setParentPlayer(Player parentPlayer) {
        this.parentPlayer = parentPlayer;
    }

    public boolean isCanSplit() {
        if (getAllCards().size() != 2) {
            return false;
        }
        if (splitted) {
            return false;
        }
        return getAllCards().get(0).getName().equals(getAllCards().get(1).getName());
    }

    public void setCanSplit(boolean canSplit) {
    }

    public int getStake() {
        return stake;
    }

    public void setStake(int stake) {
        this.stake = stake;
    }

    public int getBetting() {
        return betting;
    }

    public void setBetting(int betting) {
        this.betting = betting;
    }

    public boolean isSplitted() {
        return splitted;
    }

    public void setSplitted(boolean splitted) {
        this.splitted = splitted;
    }

    public boolean isInGame() {
        return inGame;
    }

    public void setInGame(boolean inGame) {
        this.inGame = inGame;
    }

    public PlayerDealerCommonAction getPlayerAction() {
        return playerAction;
    }

    public void setPlayerAction(PlayerDealerCommonAction playerAction) {
        this.playerAction = playerAction;
    }

    public void reset() {
        splitted = false;
        parentPlayer = null;
        inGame = false;
        setCurrentSum(new CardSum(0));
        setAllCards(new LinkedList<>());
        setResult(null);
    }
}
