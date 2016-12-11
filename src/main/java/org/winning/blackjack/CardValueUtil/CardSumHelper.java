package org.winning.blackjack.CardValueUtil;

import org.winning.blackjack.entity.Card;
import org.winning.blackjack.entity.CardSum;

public class CardSumHelper {

    public static CardSum getSum(Card card1, Card card2) {
        if( !card1.getName().equals("A")){
            return getSumOnMoreThanTwoCards(card1.getValue(), card2);
        }

        if( !card2.getName().equals("A")){
            return getSumOnMoreThanTwoCards(card2.getValue(), card1);
        }

        // both Ace
        final CardSum cardSum = new CardSum(2);
        return cardSum;
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
