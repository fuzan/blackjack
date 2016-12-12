package org.winning.blackjack.people;

import org.winning.blackjack.entity.Card;
import org.winning.blackjack.gamblingactions.PlayerDealerCommonAction;

public class Dealer extends BaseUser {

    private PlayerDealerCommonAction dealerAction;

    public Dealer(String name) {
        super(name);
    }

    public Card getSecondCard(){
        if( getAllCards().size() > 1 ){
            return getAllCards().get(1);
        }
        return null;
    }

    public PlayerDealerCommonAction getDealerAction() {
        return dealerAction;
    }

    public void setDealerAction(PlayerDealerCommonAction dealerAction) {
        this.dealerAction = dealerAction;
    }
}
