package org.winning.blackjack.CardValueUtil;

import org.winning.blackjack.entity.Card;
import org.winning.blackjack.entity.CardSum;
import org.winning.blackjack.entity.Chips;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CardSumHelper {

    public static CardSum getSum(Card card1, Card card2) {
        if (!card1.getName().equals("A")) {
            return getSumOnMoreThanTwoCards(card1.getValue(), card2);
        }

        if (!card2.getName().equals("A")) {
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

    public static int calculateBetting(List<Chips> chips) {
        final IntegerAdder bettings = new IntegerAdder(0);
        chips.stream().filter(Objects::nonNull).forEach(c -> bettings.add(c.value()));
        return bettings.getSum();
    }

    public static List<Chips> doubleChips(List<Chips> origin) {
        List<Chips> newone = new ArrayList<>();
        origin.stream().filter(Objects::nonNull).forEach(c -> {
            newone.add(c);
            newone.add(c);
        });
        return newone;
    }

    static class IntegerAdder {

        int sum;

        public IntegerAdder(int sum) {
            this.sum = sum;
        }

        public int getSum() {
            return sum;
        }

        public void add(int i) {
            sum += i;
        }
    }
}
