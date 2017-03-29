package org.winning.blackjack.gamblingactions;

import static org.winning.blackjack.entity.Result.GO_TO_DEALER;
import static org.winning.blackjack.entity.Result.JUDGE_BETTING;

import org.winning.blackjack.util.card.CardSumHelper;
import org.winning.blackjack.entity.Card;
import org.winning.blackjack.entity.Result;

public class DealerAction extends PlayerDealerCommonAction {

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
    public Result split() {
        return null;
    }

    @Override
    void dealTwoCardsToPlayer(Card firstCard, Card secondCard) {
        secondCard.setShow(false);
        player.getAllCards().add(firstCard);
        player.getAllCards().add(secondCard);
        player.setCurrentSum(CardSumHelper.getSum(firstCard, secondCard));
    }

    @Override
    public Result surrander() {
        throw new UnsupportedOperationException("dealer wont surrander !");
    }
}
