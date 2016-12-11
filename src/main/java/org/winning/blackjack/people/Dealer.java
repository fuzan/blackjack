package org.winning.blackjack.people;

import org.winning.blackjack.entity.Card;
import org.winning.blackjack.gamblingactions.DealerAction;

public class Dealer extends BaseUser {

    public static final String DEALER = "dealer";

    private DealerAction action;

    public Dealer(Card firstCard, Card secondCard) {
        super(DEALER);
        super.firstCard = firstCard;
        super.secondCard = secondCard;
    }

    public DealerAction getAction() {
        return action;
    }

    public void setAction(DealerAction action) {
        this.action = action;
    }
}
