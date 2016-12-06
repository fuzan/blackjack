package org.winning.blackjack.CardValueUtil;

import org.winning.blackjack.Card;
import org.winning.blackjack.CardSum;

public class CardSumHelper {

    public static CardSum getSum(Card card1, Card card2) {

        final CardSum sum = new CardSum(0);

        if (card1.getName().equals("A") && !card2.getName().equals("A")) {
            sum.setAlternativeSum(11 + card2.getValue());
        } else if (card2.getName().equals("A") && !card1.getName().equals("A")) {
            sum.setAlternativeSum(11 + card1.getValue());
        }

        sum.setSum(card1.getValue() + card2.getValue());
        return sum;
    }

    public static CardSum getSumOnMoreThanTwoCards(int currentSum, Card card) {
        final CardSum cardSum = new CardSum(currentSum);

        if (card.getName().equals("A")) {
            cardSum.setAlternativeSum(currentSum + 11);
        }

        cardSum.setSum(currentSum + card.getValue());
        return cardSum;
    }

    public static void copyValue(CardSum origin, CardSum newSum){
        origin.setSum(newSum.getSum());
        origin.setAlternativeSum(newSum.getAlternativeSum());
    }

}
