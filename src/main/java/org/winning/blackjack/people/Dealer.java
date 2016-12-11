package org.winning.blackjack.people;

import org.winning.blackjack.entity.Card;

public class Dealer extends BaseUser{


    public static final String DEALER = "dealer";

    public Dealer(Card firstCard, Card secondCard) {
        super(DEALER);
        super.firstCard = firstCard;
        super.secondCard = secondCard;
    }

}
