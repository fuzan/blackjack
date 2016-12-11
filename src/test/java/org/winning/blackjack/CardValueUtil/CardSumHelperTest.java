package org.winning.blackjack.CardValueUtil;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.winning.blackjack.entity.Card;
import org.winning.blackjack.entity.CardSum;
import org.winning.blackjack.entity.Color;

import java.util.LinkedList;
import java.util.List;

public class CardSumHelperTest {

    private Card card1;
    private Card card2;
    private List<Card> cards;

    @Before
    public void setUp() throws Exception {
        card1 = new Card(Color.CLUB, "three");
        card2 = new Card(Color.HEART, "A");

        cards = new LinkedList<>();
        cards.add(card1);
        cards.add(card2);

    }

    @Test
    public void getSum() throws Exception {
        CardSum sum = CardSumHelper.getSum(card1, card2);
        Assert.assertTrue(sum.getSum() == 4);
        Assert.assertTrue(sum.getAlternativeSum() == 14);

        Card card3 = new Card(Color.HEART, "A");
        sum = CardSumHelper.getSum(card2, card3);
        Assert.assertTrue(sum.getSum() == 2);
        Assert.assertTrue(sum.getAlternativeSum() == 0);


    }

    @Test
    public void getSumOnMoreThanTwoCards() throws Exception {

    }

}