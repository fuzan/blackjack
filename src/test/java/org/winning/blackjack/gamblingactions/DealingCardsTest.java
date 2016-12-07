package org.winning.blackjack.gamblingactions;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.winning.blackjack.Card;
import org.winning.blackjack.Color;
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

        player = new Player();

    }

    @Test
    public void dealCardsToPlayerOrDealer() throws Exception {
        dealingCards.dealTwoFaceUpCardsToPlayer(card1, card2, player);
        Assert.assertNotEquals(player.isBlackJack(), true);

        Card card3 = new Card(Color.CLUB, "J");
        dealingCards.dealTwoFaceUpCardsToPlayer(card1, card3, player);
        Assert.assertTrue(player.isBlackJack());

    }

    @Test
    public void dealTwoFaceUpCardsToPlayer() throws Exception {

    }

}