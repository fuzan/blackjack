package org.winning.blackjack.gamblingactions;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.winning.blackjack.entity.Card;
import org.winning.blackjack.entity.Color;
import org.winning.blackjack.people.Player;

public class PlayerDealerCommonActionTest {

    private Player player;
    private Card card1;
    private Card card2;
    private PlayerAction action;

    @Before
    public void setUp() throws Exception {
        card1 = new Card(Color.CLUB, "A");
        card2 = new Card(Color.CLUB, "two");

        player = new Player("tester");
        action = new PlayerAction();
        player.setBindingAction(action);
    }

    @Test
    public void test_dealCardsToPlayerOrDealer() throws Exception {
        action.dealTwoCardsToPlayer(card1, card2);
        Assert.assertNotEquals(player.isBlackJack(), true);
        Assert.assertEquals(player.getCurrentSum().getSum(), 3);
        Assert.assertEquals(player.getCurrentSum().getAlternativeSum(), 13);

        Card card3 = new Card(Color.CLUB, "J");
        action.dealTwoCardsToPlayer(card1, card3);
        Assert.assertFalse(player.isBlackJack());

        Card card4 = new Card(Color.CLUB, "five");
        action.dealTwoCardsToPlayer(card2, card4);
        Assert.assertEquals(player.getCurrentSum().getSum(), 7);
        Assert.assertEquals(player.getCurrentSum().getAlternativeSum(), 0);
        Assert.assertEquals(player.isBlackJack(), false);

        action.dealTwoCardsToPlayer(card1, card1);
        Assert.assertEquals(player.getCurrentSum().getSum(), 2);
        Assert.assertEquals(player.getCurrentSum().getAlternativeSum(), 0);
        Assert.assertEquals(player.isBlackJack(), false);

    }

    @Test
    public void test_dealTwoCardsToPlayer() throws Exception {
        action.dealTwoCardsToPlayer(card1, card2);
        action.dealCardsToPlayerOrDealer(card1);

        Assert.assertEquals(player.getCurrentSum().getSum(), 4);
        Assert.assertEquals(player.getCurrentSum().getAlternativeSum(), 14);

        Card card3 = new Card(Color.CLUB, "J");
        action.dealTwoCardsToPlayer(card2, card3);

        Assert.assertEquals(player.getCurrentSum().getSum(), 12);
        Assert.assertEquals(player.getCurrentSum().getAlternativeSum(), 0);
    }

}