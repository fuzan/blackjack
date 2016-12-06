package org.winning.blackjack.gamblingactions;

import org.winning.blackjack.Card;
import org.winning.blackjack.CardSum;
import org.winning.blackjack.CardValueUtil.CardSumHelper;
import org.winning.blackjack.people.BaseUser;

public class DealingCards {

    public CardSum dealCardsToPlayerOrDealer(BaseUser player, Card card){
        card.setShow(true);
        final CardSum sum = CardSumHelper.getSumOnMoreThanTwoCards(player.getTotalSum(), card);
        if( sum.getSum() < 21 ){
            player.getOtherCards().add(card);
        }else if( sum.getSum() == 21 || sum.getAlternativeSum() == 21){
            player.getOtherCards().add(card);
            //player.setResult(Result.WIN);
        }
        else{
            // this is should be in judge not in action
            //player.setResult(Result.BUSTED);
        }

        return sum;
    }

    public void dealTwoFaceUpCardsToPlayer(Card firstCard, Card secondCard, BaseUser player){
        firstCard.setShow(true);
        secondCard.setShow(true);
        player.setFirstCard(firstCard);
        player.setSecondCard(secondCard);
        player.setTotalSum(CardSumHelper.getSum(firstCard, secondCard).getSum());
    }


}
