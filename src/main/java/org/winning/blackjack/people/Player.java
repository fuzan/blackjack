package org.winning.blackjack.people;
import org.winning.blackjack.Card;

public class Player extends BaseUser{

    public Player(Card firstCard, Card secondCard) {
        super.firstCard = firstCard;
        super.secondCard = secondCard;
    }

}
