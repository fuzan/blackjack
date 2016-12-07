package org.winning.blackjack.gamblingactions;

import org.winning.blackjack.Card;
import org.winning.blackjack.CardSum;
import org.winning.blackjack.CardValueUtil.CardSumHelper;
import org.winning.blackjack.people.BaseUser;

public class DealingCards {

    public CardSum dealCardsToPlayerOrDealer(BaseUser player, Card card) {
        card.setShow(true);
        player.getOtherCards().add(card);
        //dealing with no soft
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

    public void dealTwoFaceUpCardsToPlayer(Card firstCard, Card secondCard, BaseUser player) {
        firstCard.setShow(true);
        secondCard.setShow(true);
        player.setFirstCard(firstCard);
        player.setSecondCard(secondCard);
        player.setCurrentSum(CardSumHelper.getSum(firstCard, secondCard));
    }

    //if alternative value is not 0, then is soft
    private boolean isSoft(BaseUser player) {
        return player.getCurrentSum().getAlternativeSum() == 0 ? false : true;
    }
}
