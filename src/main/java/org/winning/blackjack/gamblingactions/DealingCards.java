package org.winning.blackjack.gamblingactions;

import org.winning.blackjack.CardValueUtil.BJLogger;
import org.winning.blackjack.CardValueUtil.CardSumHelper;
import org.winning.blackjack.entity.Card;
import org.winning.blackjack.entity.CardSum;
import org.winning.blackjack.people.BaseUser;
import org.winning.blackjack.people.Player;

public class DealingCards {

    private BJLogger logger = new BJLogger();

    public CardSum dealCardsToPlayerOrDealer(BaseUser player, Card card) {
        card.setShow(true);
        if (player.getSecondCard() == null) {
            dealSecondCardToPlayer(player, card);
        } else {
            player.getOtherCards().add(card);
        }
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

    public void dealTwoCardsToPlayer(Card firstCard, Card secondCard, BaseUser player) {
        player.setFirstCard(firstCard);
        player.setSecondCard(secondCard);
        player.setCurrentSum(CardSumHelper.getSum(firstCard, secondCard));
    }

    public void splitPrompt(Player player){
        if(player.isCanSplit()){
            logger.pleaseConsiderSplit(player);
        }
    }

    private void dealSecondCardToPlayer(BaseUser player, Card card) {
        dealTwoCardsToPlayer(player.getFirstCard(), card, player);
    }

    //if alternative value is not 0, then is soft
    private boolean isSoft(BaseUser player) {
        return player.getCurrentSum().getAlternativeSum() == 0 ? false : true;
    }
}
