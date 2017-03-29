package org.winning.blackjack.people;

import org.winning.blackjack.entity.CardSum;
import org.winning.blackjack.entity.Chips;

import java.util.LinkedList;
import java.util.List;

public class Player extends BaseUser {

    private int stake;
    private List<Chips> betting;
    private boolean splitted;
    private Player parentPlayer;
    private SplitPlayer splitedPlayer;
    private boolean inGame;

    public Player(String name) {
        super(name);
    }

    public SplitPlayer getSplitedPlayer() {
        return splitedPlayer;
    }

    public void setSplitedPlayer(SplitPlayer splitedPlayer) {
        this.splitedPlayer = splitedPlayer;
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

    public List<Chips> getBetting() {
        return betting;
    }

    public void setBetting(List<Chips> betting) {
        this.betting = betting;
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
