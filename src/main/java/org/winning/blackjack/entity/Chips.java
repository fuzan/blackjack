package org.winning.blackjack.entity;

public enum Chips {
    FIVE(5),
    TEN(10),
    TWENTY(20),
    FIFTY(50),
    HUNDRED(100),
    TWO_HUNDRED(200),
    FIVE_HUNDRED(500),
    ONE_K(1000),
    FIVE_K(5000),
    TEN_K(10000),
    TWENTY_K(20000),
    HUNDREN_K(100000);

    private int value;

    Chips(int value) {
        this.value = value;
    }

    public int value() {
        return this.value;
    }
}
