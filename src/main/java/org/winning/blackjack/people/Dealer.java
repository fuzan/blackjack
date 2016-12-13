package org.winning.blackjack.people;

import org.winning.blackjack.entity.Card;

public class Dealer extends BaseUser {

    public Dealer(String name) {
        super(name);
    }

    public Card getSecondCard() {
        if (getAllCards().size() > 1) {
            return getAllCards().get(1);
        }
        return null;
    }
}
