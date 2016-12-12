package org.winning.blackjack.gamblingactions;

import static org.winning.blackjack.entity.Result.BUSTED;
import static org.winning.blackjack.entity.Result.GO_TO_DEALER;
import static org.winning.blackjack.entity.Result.JUDGE_BETTING;

import org.winning.blackjack.CardValueUtil.CardSumHelper;
import org.winning.blackjack.entity.Card;
import org.winning.blackjack.entity.Result;
import org.winning.blackjack.people.Dealer;
import org.winning.blackjack.people.Player;

public class DealerAction extends PlayerDealerCommonAction {

    public DealerAction(Dealer dealer) {
        super(dealer);
        dealer.setDealerAction(this);
    }

    @Override
    public Result hit(Card card) {
        // if dealer is soft
        //hit to soft17
        while (player.getCurrentSum().getSum() < 17) {
            if (player.getCurrentSum().getAlternativeSum() > 17
                && player.getCurrentSum().getAlternativeSum() <= 21) {
                return JUDGE_BETTING;
            }
            dealCardsToPlayerOrDealer(card);
        }

        if (player.getCurrentSum().getSum() > 21) {
            player.setResult(BUSTED);
        }
        // do nothing if 17 to 21
        // dealer turn again if less than 17
        return GO_TO_DEALER;
    }

    @Override
    public Result stand() {
        // go to final judge
        return JUDGE_BETTING;
    }

    @Override
    public Result double_betting(Card card) {
        return null;
    }

    @Override
    public Player[] split() {
        return null;
    }

    @Override
    void dealTwoCardsToPlayer(Card firstCard, Card secondCard) {
        secondCard.setShow(false);
        player.getAllCards().add(firstCard);
        player.getAllCards().add(secondCard);
        player.setCurrentSum(CardSumHelper.getSum(firstCard, secondCard));
    }
}
