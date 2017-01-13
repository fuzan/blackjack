package org.winning.blackjack.people;

import org.winning.blackjack.entity.Card;
import org.winning.blackjack.entity.CardSum;
import org.winning.blackjack.gamblingactions.PlayerAction;

import java.util.LinkedList;
import java.util.List;

public class SplitPlayer {

    private List<Player> splitPlayers;

    // first and second cards are identical
    public SplitPlayer(String name1, String name2, Player parentPlayer) {

        splitPlayers = new LinkedList<>();
        Card firstCard = parentPlayer.getAllCards().get(0);

        final Player player1 = new Player(name1);
        player1.getAllCards().add(firstCard);
        player1.setCurrentSum(getCardValue(firstCard));
        player1.setBetting(parentPlayer.getBetting());
        player1.setSplitted(true);
        player1.setCanSplit(false);

        final Player player2 = new Player(name2);
        player2.getAllCards().add(firstCard);

        player2.setCurrentSum(getCardValue(firstCard));
        player2.setBetting(parentPlayer.getBetting());
        player2.setSplitted(true);
        player2.setCanSplit(false);

        player1.setParentPlayer(parentPlayer);
        player2.setParentPlayer(parentPlayer);

        player1.setBindingAction(new PlayerAction());
        player2.setBindingAction(new PlayerAction());

        parentPlayer.setSplitted(true);
        splitPlayers.add(player1);
        splitPlayers.add(player2);
    }

    public List<Player> getSplitPlayers() {
        return splitPlayers;
    }

    public void setSplitPlayers(List<Player> splitPlayers) {
        this.splitPlayers = splitPlayers;
    }

    private CardSum getCardValue(Card card) {
        final CardSum sum = new CardSum(card.getValue());
        if ("A".equals(card.getName())) {
            sum.setAlternativeSum(11);
        }
        return sum;
    }
}
