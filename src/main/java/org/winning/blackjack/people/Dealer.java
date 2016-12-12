package org.winning.blackjack.people;

import org.winning.blackjack.entity.Card;
import org.winning.blackjack.gamblingactions.DealerAction;

public class Dealer extends BaseUser {

    private DealerAction dealerAction;

    public Dealer(String name) {
        super(name);
    }

    public Card getSecondCard(){
        if( getAllCards().size() > 1 ){
            return getAllCards().get(1);
        }
        return null;
    }

    public DealerAction getDealerAction() {
        return dealerAction;
    }

    public void setDealerAction(DealerAction dealerAction) {
        this.dealerAction = dealerAction;
    }
}
