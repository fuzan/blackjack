package org.winning.blackjack.CardValueUtil;

import org.junit.Before;
import org.junit.Test;
import org.winning.blackjack.entity.Card;
import org.winning.blackjack.entity.Color;
import org.winning.blackjack.people.Player;
import org.winning.blackjack.util.card.BJLogger;

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

        player.getAllCards().add(card6);
        player.getAllCards().add(card7);
        bjLogger.logPlayerOrDealerStatus(player);

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