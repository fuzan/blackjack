package org.winning.blackjack.people;
public class Player extends BaseUser{

    private int stake;
    private int betting;

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
}
