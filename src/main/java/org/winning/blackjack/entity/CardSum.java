package org.winning.blackjack.entity;

public class CardSum {

    private int sum;
    private int alternativeSum;

    public CardSum(int sum) {
        this.sum = sum;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public int getAlternativeSum() {
        return alternativeSum;
    }

    public void setAlternativeSum(int alternativeSum) {
        this.alternativeSum = alternativeSum;
    }
}
