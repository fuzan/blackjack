package org.winning.blackjack.gamblingactions;

import org.winning.blackjack.CardValueUtil.CardSumHelper;
import org.winning.blackjack.entity.Card;
import org.winning.blackjack.entity.CardSum;
import org.winning.blackjack.people.BaseUser;

public abstract class PlayerDealerCommonAction implements BlackJackAction {

    protected BaseUser player;

    public PlayerDealerCommonAction(BaseUser player) {
        this.player = player;
    }

    abstract void dealTwoCardsToPlayer(Card firstCard, Card secondCard);

    CardSum dealCardsToPlayerOrDealer(Card card) {
        card.setShow(true);
        player.getAllCards().add(card);
        final CardSum sum = CardSumHelper.getSumOnMoreThanTwoCards(player.getCurrentSum().getSum(), card);

        //dealing with soft card
        if (isSoft(player)) {
            final CardSum sum2 =
                    CardSumHelper.getSumOnMoreThanTwoCards(player.getCurrentSum().getAlternativeSum(), card);
            sum.setAlternativeSum(sum2.getSum());
        }

        player.setCurrentSum(sum);
        return sum;
    }

    //if alternative value is not 0, then is soft
    private boolean isSoft(BaseUser player) {
        return player.getCurrentSum().getAlternativeSum() == 0 ? false : true;
    }
}
