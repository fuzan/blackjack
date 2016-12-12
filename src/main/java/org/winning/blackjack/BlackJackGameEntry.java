package org.winning.blackjack;

import org.winning.blackjack.CardValueUtil.BJLogger;
import org.winning.blackjack.gamblingactions.BlackJackGame;
import org.winning.blackjack.gamblingactions.BlackJackStateMachine;
import org.winning.blackjack.gamblingactions.DealerAction;
import org.winning.blackjack.gamblingactions.PlayerAction;
import org.winning.blackjack.gamblingactions.PlayerDealerCommonAction;
import org.winning.blackjack.input.PlayerInteractionInput;
import org.winning.blackjack.people.Dealer;
import org.winning.blackjack.people.Player;

import java.util.LinkedList;
import java.util.List;

public class BlackJackGameEntry {

    public static final int DECK_NUMBER = 2;

    public static void main(String[] args) {

        Dealer dealer = new Dealer("dealer");

        Player player1 = new Player("player1");
        player1.setStake(1000);

        Player player2 = new Player("player2");
        player2.setStake(1000);

        Player player3 = new Player("player3");
        player3.setStake(1000);

        PlayerDealerCommonAction action1 = new PlayerAction(player1);
        PlayerDealerCommonAction action2 = new PlayerAction(player2);
        PlayerDealerCommonAction action3 = new PlayerAction(player3);

        PlayerDealerCommonAction dealerAction = new DealerAction(dealer);

        List<Player> players = new LinkedList<>();
        players.add(player1);
        players.add(player2);
        players.add(player3);

        final BlackJackGame blackJackGame = new BlackJackGame(dealer, players, DECK_NUMBER, new BJLogger());
        BlackJackStateMachine machine = new BlackJackStateMachine(new PlayerInteractionInput());
        machine.playBlackJack(blackJackGame);


    }
}