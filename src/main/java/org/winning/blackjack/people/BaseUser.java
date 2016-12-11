package org.winning.blackjack.people;

import org.winning.blackjack.entity.Card;
import org.winning.blackjack.entity.CardSum;
import org.winning.blackjack.CardValueUtil.CardSumHelper;
import org.winning.blackjack.entity.Result;

import java.util.LinkedList;
import java.util.List;

public class BaseUser {

    private String name;
    protected Card firstCard;
    protected Card secondCard;
    private List<Card> otherCards = new LinkedList<>();
    private CardSum currentSum;

    private Result result;
    private boolean isBlackJack;

    public BaseUser(String name) {
        this.name = name;
    }

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

    public CardSum getCurrentSum() {
        return currentSum;
    }

    public void setCurrentSum(CardSum currentSum) {
        this.currentSum = currentSum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
