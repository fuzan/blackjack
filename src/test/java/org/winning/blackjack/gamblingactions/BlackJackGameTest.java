package org.winning.blackjack.gamblingactions;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.winning.blackjack.util.card.BJLogger;
import org.winning.blackjack.people.Dealer;
import org.winning.blackjack.people.Player;

import java.util.LinkedList;
import java.util.List;

public class BlackJackGameTest {

    private Dealer dealer;
    private Player player1;
    private Player player2;
    private Player player3;
    private List<Player> players;
    private BlackJackGame game;

    @Before
    public void setUp() throws Exception {

        dealer = new Dealer("dealer");
        dealer.setBindingAction(new DealerAction());

        player1 = new Player("player1");
        player1.setBindingAction(new PlayerAction());
        player1.setStake(10000);

        player2 = new Player("player2");
        player2.setBindingAction(new PlayerAction());
        player2.setStake(10000);

        player3 = new Player("player3");
        player3.setBindingAction(new PlayerAction());
        player3.setStake(10000);

        players = new LinkedList<>();
        players.add(player1);
        players.add(player2);
        players.add(player3);

        game = new BlackJackGame(dealer,players,2,new BJLogger());
    }

    @Test
    public void ifPlayerDontWantToPlay() throws Exception{
        game.ifPlayerDontWantToPlay(player1, false);
        Assert.assertFalse(player1.isInGame());
    }

    @Test
    public void dealCardToDealer() throws Exception {
        game.dealCardsToDealer();
        Assert.assertTrue(dealer.getAllCards().get(0).isShow());
        Assert.assertFalse(dealer.getAllCards().get(1).isShow());
        int card1 = dealer.getAllCards().get(0).getValue();
        int card2 = dealer.getAllCards().get(1).getValue();
        Assert.assertEquals(card1 + card2, dealer.getCurrentSum().getSum());
    }

    @Test
    public void begin() throws Exception{
        game.begin(player1);
        Assert.assertEquals(player1.getStake(), 10000);
    }
    @Test
    public void setStandbyPlayers() throws Exception {

    }

    @Test
    public void getDeckNumber() throws Exception {

    }

    @Test
    public void setDeckNumber() throws Exception {

    }

    @Test
    public void getCards() throws Exception {

    }

    @Test
    public void endGamePlayer() throws Exception {

    }

    @Test
    public void clearStandBy() throws Exception {

    }

    @Test
    public void askPlayer() throws Exception {


    }

}