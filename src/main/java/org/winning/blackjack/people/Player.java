package org.winning.blackjack.people;

import org.winning.blackjack.entity.CardSum;
import org.winning.blackjack.gamblingactions.DealingCards;
import org.winning.blackjack.gamblingactions.PlayerAction;

import java.util.LinkedList;

public class Player extends BaseUser {

    private int stake;
    private int betting;
    private boolean splitted;
    private Player parentPlayer;
    private Player[] twoSplitedPlayer = new Player[2];
    private boolean inGame;

    private PlayerAction action;

    public Player(String name) {
        super(name);
    }

    public PlayerAction getAction() {
        return action == null ? new PlayerAction(new DealingCards()) : action;
    }

    public void setAction(PlayerAction action) {
        this.action = action;
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
        if (firstCard != null && secondCard != null && getOtherCards().size() > 0) {
            return false;
        }
        if (splitted) {
            return false;
        }
        return firstCard.getName().equals(secondCard.getName());
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

    public void reset() {
        splitted = false;
        parentPlayer = null;
        firstCard = null;
        secondCard = null;
        inGame = false;
        setCurrentSum(new CardSum(0));
        setOtherCards(new LinkedList<>());
        setResult(null);
    }
}
