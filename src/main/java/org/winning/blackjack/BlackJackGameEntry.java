package org.winning.blackjack;

import org.winning.blackjack.CardValueUtil.BJLogger;
import org.winning.blackjack.gamblingactions.BlackJackGame;
import org.winning.blackjack.gamblingactions.BlackJackStateMachine;
import org.winning.blackjack.gamblingactions.DealerAction;
import org.winning.blackjack.gamblingactions.DealingCards;
import org.winning.blackjack.gamblingactions.PlayerAction;
import org.winning.blackjack.input.PlayerInteractionInput;
import org.winning.blackjack.people.Dealer;
import org.winning.blackjack.people.Player;

import java.util.LinkedList;
import java.util.List;

public class BlackJackGameEntry {

    public static final int DECK_NUMBER = 2;

    public static void main(String[] args) {

        Dealer dealer = new Dealer(null,null);
        dealer.setName("dealer1");

        Player player1 = new Player("player1");
        player1.setStake(10000);

        Player player2 = new Player("player2");
        player2.setStake(99999);

        Player player3 = new Player("player3");
        player3.setStake(99999);

        DealingCards dc = new DealingCards();
        PlayerAction action = new PlayerAction(dc);
        DealerAction dealerAction = new DealerAction(dc);

        player1.setAction(action);
        player2.setAction(action);
        player3.setAction(action);
        dealer.setAction(dealerAction);

        List<Player> players = new LinkedList<>();
        players.add(player1);
        players.add(player2);
        players.add(player3);

        final BlackJackGame blackJackGame = new BlackJackGame(dealer, players, DECK_NUMBER, dc, new BJLogger());
        BlackJackStateMachine machine = new BlackJackStateMachine(new PlayerInteractionInput());
        machine.playBlackJack(blackJackGame);

    }
}