package org.winning.blackjack.gamblingactions;

import static org.winning.blackjack.entity.Result.BUSTED;
import static org.winning.blackjack.entity.Result.GO_TO_DEALER;
import static org.winning.blackjack.entity.Result.JUDGE_BETTING;

import org.winning.blackjack.entity.Card;
import org.winning.blackjack.entity.Result;
import org.winning.blackjack.people.BaseUser;
import org.winning.blackjack.people.Player;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class DealerAction implements BlackJackAction {

    private DealingCards dealingCards;

    public DealerAction(DealingCards dealingCards) {
        this.dealingCards = dealingCards;
    }

    @Override
    public Result hit(BaseUser player, Card card) {
        // if dealer is soft
        //hit to soft17
        while (player.getCurrentSum().getSum() < 17) {
            if (player.getCurrentSum().getAlternativeSum() > 17
                && player.getCurrentSum().getAlternativeSum() <= 21) {
                return JUDGE_BETTING;
            }
            dealingCards.dealCardsToPlayerOrDealer(player, card);
        }

        if (player.getCurrentSum().getSum() > 21) {
            player.setResult(BUSTED);
        }
        // do nothing if 17 to 21
        // dealer turn again if less than 17
        return GO_TO_DEALER;
    }

    @Override
    public Result stand(BaseUser player) {
        // go to final judge
        return JUDGE_BETTING;
    }

    @Override
    public Result double_betting(BaseUser player, Card card) {
        throw new NotImplementedException();
    }

    @Override
    public Player[] split(BaseUser player) {
        throw new NotImplementedException();
    }
}
