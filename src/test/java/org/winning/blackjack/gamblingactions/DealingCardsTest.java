package org.winning.blackjack.gamblingactions;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.winning.blackjack.entity.Card;
import org.winning.blackjack.entity.Color;
import org.winning.blackjack.people.Player;

public class DealingCardsTest {

    private Player player;
    private DealingCards dealingCards = new DealingCards();
    private Card card1;
    private Card card2;

    @Before
    public void setUp() throws Exception {
        card1 = new Card(Color.CLUB, "A");
        card2 = new Card(Color.CLUB, "two");

        player = new Player("tester");
    }

    @Test
    public void dealCardsToPlayerOrDealer() throws Exception {
        dealingCards.dealTwoCardsToPlayer(card1, card2, player);
        Assert.assertNotEquals(player.isBlackJack(), true);
        Assert.assertEquals(player.getCurrentSum().getSum(), 3);
        Assert.assertEquals(player.getCurrentSum().getAlternativeSum(), 13);

        Card card3 = new Card(Color.CLUB, "J");
        dealingCards.dealTwoCardsToPlayer(card1, card3, player);
        Assert.assertTrue(player.isBlackJack());

        Card card4 = new Card(Color.CLUB, "five");
        dealingCards.dealTwoCardsToPlayer(card2, card4, player);
        Assert.assertEquals(player.getCurrentSum().getSum(), 7);
        Assert.assertEquals(player.getCurrentSum().getAlternativeSum(), 0);
        Assert.assertEquals(player.isBlackJack(), false);

        dealingCards.dealTwoCardsToPlayer(card1, card1, player);
        Assert.assertEquals(player.getCurrentSum().getSum(), 2);
        Assert.assertEquals(player.getCurrentSum().getAlternativeSum(), 0);
        Assert.assertEquals(player.isBlackJack(), false);

    }

    @Test
    public void dealTwoFaceUpCardsToPlayer() throws Exception {
        dealingCards.dealTwoCardsToPlayer(card1, card2, player);
        dealingCards.dealCardsToPlayerOrDealer(player, card1);

        Assert.assertEquals(player.getCurrentSum().getSum(), 4);
        Assert.assertEquals(player.getCurrentSum().getAlternativeSum(), 14);

        Card card3 = new Card(Color.CLUB, "J");
        dealingCards.dealTwoCardsToPlayer(card2, card3, player);

        Assert.assertEquals(player.getCurrentSum().getSum(), 12);
        Assert.assertEquals(player.getCurrentSum().getAlternativeSum(), 0);
    }

}