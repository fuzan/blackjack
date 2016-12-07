package org.winning.blackjack.people;

import org.winning.blackjack.Card;

public class Dealer extends BaseUser{


    public Dealer(Card firstCard, Card secondCard) {
        secondCard.setShow(false);
        super.firstCard = firstCard;
        super.secondCard = secondCard;
    }

}
