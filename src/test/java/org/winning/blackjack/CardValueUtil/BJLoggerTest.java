package org.winning.blackjack.CardValueUtil;

import org.junit.Before;
import org.junit.Test;
import org.winning.blackjack.entity.Card;
import org.winning.blackjack.entity.Color;
import org.winning.blackjack.gamblingactions.DealingCards;
import org.winning.blackjack.people.Player;

public class BJLoggerTest {

    private BJLogger bjLogger;

    @Before
    public void setUp() throws Exception {
        bjLogger = new BJLogger();

    }

    @Test
    public void logPlayerOrDealerFirstTwoCard() throws Exception {
        Player player = new Player("test1");
        Card card6 = new Card(Color.SPADE, "six");
        Card card7 = new Card(Color.SPADE, "seven");

        DealingCards dealingCards = new DealingCards();

        dealingCards.dealTwoCardsToPlayer(card6, card7, player);
        bjLogger.logPlayerOrDealerFirstTwoCard(player);


        Card card2 = new Card(Color.SPADE, "Q");
        dealingCards.dealCardsToPlayerOrDealer(player, card2);

        bjLogger.logPlayerNextCard(player);

    }

    @Test
    public void logPlayerNextCard() throws Exception {

    }

    @Test
    public void logPlayerBehavior() throws Exception {

    }

    @Test
    public void logDealerBehavior() throws Exception {

    }

}