package org.winning.blackjack.people;

import org.winning.blackjack.CardValueUtil.CardSumHelper;
import org.winning.blackjack.entity.Card;
import org.winning.blackjack.entity.CardSum;
import org.winning.blackjack.entity.Result;

import java.util.LinkedList;
import java.util.List;

public class BaseUser {

    private String name;
    private List<Card> allCards = new LinkedList<>();
    private CardSum currentSum;
    private Result result;
    private boolean isBlackJack;

    public BaseUser(String name) {
        this.name = name;
    }

    public boolean isBlackJack() {
        if (allCards.size() < 2) {
            return false;
        }
        Card firstCard = allCards.get(0);
        Card secondCard = allCards.get(1);
        CardSum sum = CardSumHelper.getSum(firstCard, secondCard);
        if (sum.getSum() == 21 || sum.getAlternativeSum() == 21) {
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

    public List<Card> getAllCards() {
        return allCards;
    }

    public void setAllCards(List<Card> allCards) {
        this.allCards = allCards;
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
