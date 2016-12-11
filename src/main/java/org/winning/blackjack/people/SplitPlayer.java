package org.winning.blackjack.people;

import org.winning.blackjack.entity.CardSum;

public class SplitPlayer{

    private Player player1;
    private Player player2;

    // first and second cards are identical
    public SplitPlayer(String name1, String name2, Player parentPlayer) {

        player1 = new Player(name1);
        player1.setFirstCard(parentPlayer.getFirstCard());
        player1.setCurrentSum(new CardSum(0));
        player1.setBetting(parentPlayer.getBetting());
        player1.setSplitted(true);
        player1.setCanSplit(false);

        player2 = new Player(name2);
        player2.setFirstCard(parentPlayer.getFirstCard());
        player2.setCurrentSum(new CardSum(0));
        player2.setBetting(parentPlayer.getBetting());
        player2.setSplitted(true);
        player2.setCanSplit(false);

        parentPlayer.getTwoSplitedPlayer()[0] = player1;
        parentPlayer.getTwoSplitedPlayer()[1] = player2;
        player1.setParentPlayer(parentPlayer);
        player2.setParentPlayer(parentPlayer);
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

}
