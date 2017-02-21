package org.winning.blackjack.CardValueUtil;

import org.winning.blackjack.entity.Card;
import org.winning.blackjack.entity.CardSum;
import org.winning.blackjack.entity.Chips;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CardSumHelper {

    public static CardSum getSum(Card card1, Card card2) {

        // both Ace
        if (card1.getName().equals("A") && card2.getName().equals("A")) {
            return new CardSum(2);
        }

        if (card1.getName().equals("A") || card2.getName().equals("A")) {
            return getSum(card1.getValue(), card2.getValue(), true);
        }
        return getSum(card1.getValue(), card2.getValue(), false);
    }

    private static CardSum getSum(int cardValue, int cardValue2, boolean isAce) {
        final CardSum cardSum = new CardSum(0);
        cardSum.setSum(cardValue + cardValue2);
        if (isAce) {
            cardSum.setAlternativeSum(cardValue + cardValue2 + 10);
            return cardSum;
        }
        return cardSum;
    }

    public static int calculateBetting(List<Chips> chips) {
        final IntegerAdder bettings = new IntegerAdder(0);
        chips.stream().filter(Objects::nonNull).forEach(c -> bettings.add(c.value()));
        return bettings.getSum();
    }

    public static List<Chips> doubleChips(List<Chips> origin) {
        final List<Chips> newone = new ArrayList<>();
        origin.stream().filter(Objects::nonNull).forEach(c -> {
            newone.add(c);
            newone.add(c);
        });
        return newone;
    }

    public static CardSum getSumOnMoreThanTwoCards(int sum, Card card) {
        if (card.getName().equals("A")) {
            return getSum(sum, card.getValue(), true);
        }
        return getSum(sum, card.getValue(), false);
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
