package org.winning.blackjack.people;

import org.winning.blackjack.Card;
import org.winning.blackjack.CardSum;
import org.winning.blackjack.CardValueUtil.CardSumHelper;
import org.winning.blackjack.Result;

import java.util.List;

public class BaseUser {

    protected Card firstCard;
    protected Card secondCard;
    private List<Card> otherCards;
    private int totalSum;
    private Result result;
    private boolean isBlackJack;

    public boolean isBlackJack() {
        CardSum sum = CardSumHelper.getSum(firstCard, secondCard);
        if( sum.getSum() == 21 || sum.getAlternativeSum() == 21) {
            return true;
        }
        return false;
    }

    public void setBlackJack(boolean blackJack) {
        isBlackJack = blackJack;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Card getFirstCard() {
        return firstCard;
    }

    public void setFirstCard(Card firstCard) {
        this.firstCard = firstCard;
    }

    public Card getSecondCard() {
        return secondCard;
    }

    public void setSecondCard(Card secondCard) {
        this.secondCard = secondCard;
    }

    public List<Card> getOtherCards() {
        return otherCards;
    }

    public void setOtherCards(List<Card> otherCards) {
        this.otherCards = otherCards;
    }

    public int getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(int totalSum) {
        this.totalSum = totalSum;
    }
}
