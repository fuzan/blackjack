package org.winning.blackjack.people;

import org.winning.blackjack.Card;

public class Dealer extends BaseUser{


    public Dealer(Card firstCard, Card secondCard) {
        super.firstCard = firstCard;
        super.secondCard = secondCard;
    }

}
