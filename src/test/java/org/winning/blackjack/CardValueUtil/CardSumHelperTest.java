package org.winning.blackjack.CardValueUtil;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.winning.blackjack.CardValueUtil.CardSumHelper.calculateBetting;
import static org.winning.blackjack.CardValueUtil.CardSumHelper.getSumOnMoreThanTwoCards;

import org.junit.Before;
import org.junit.Test;
import org.winning.blackjack.entity.Card;
import org.winning.blackjack.entity.CardSum;
import org.winning.blackjack.entity.Chips;
import org.winning.blackjack.entity.Color;

import java.util.ArrayList;
import java.util.List;

public class CardSumHelperTest {

    private Card card1;
    private Card card2;
    private List<Card> cards;

    @Before
    public void setUp() throws Exception {
        card1 = new Card(Color.CLUB, "three");
        card2 = new Card(Color.HEART, "A");

        cards = new ArrayList<>();
        cards.add(card1);
        cards.add(card2);
    }

    @Test
    public void getSum() {
        CardSum sum = CardSumHelper.getSum(card1, card2);
        assertEquals(sum.getSum(), 4);
        assertEquals(sum.getAlternativeSum(), 14);

        Card card3 = new Card(Color.HEART, "A");
        sum = CardSumHelper.getSum(card2, card3);
        assertEquals(sum.getSum(), 2);
        assertEquals(sum.getAlternativeSum(), 0);

        Card card4 = new Card(Color.HEART, "two");
        sum = CardSumHelper.getSum(card1, card4);
        assertEquals(sum.getSum(), 5);
    }

    @Test
    public void getSumMoreThanOneCards() {
        CardSum sum = getSumOnMoreThanTwoCards(0, card1);
        assertTrue(sum.getSum() == 3);
        sum = getSumOnMoreThanTwoCards(sum.getSum(), card2);
        assertTrue(sum.getSum() == 4);
        assertTrue(sum.getAlternativeSum() == 14);
    }

    @Test
    public void calculateSum() {
        final int sum = calculateBetting(constructChips());
        assertTrue(sum == 85);
    }

    @Test
    public void doubleChips() {
        final List<Chips> chipses = CardSumHelper.doubleChips(constructChips());
        final int sum = calculateBetting(chipses);
        assertTrue(sum == 170);
    }

    private List<Chips> constructChips() {
        final List<Chips> chipses = new ArrayList<>();
        chipses.add(Chips.FIFTY);
        chipses.add(Chips.TEN);
        chipses.add(Chips.FIVE);
        chipses.add(Chips.TWENTY);
        return chipses;
    }
}