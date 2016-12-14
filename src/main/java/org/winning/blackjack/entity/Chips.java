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

    public static Chips getChip(int betting){
        Chips chip;
        switch (betting) {
            case 5:
                chip = Chips.FIVE;
                break;
            case 10:
                chip = Chips.TEN;
                break;
            case 20:
                chip = Chips.TWENTY;
                break;
            case 50:
                chip = Chips.FIFTY;
                break;
            case 100:
                chip = Chips.HUNDRED;
                break;
            case 200:
                chip = Chips.TWO_HUNDRED;
                break;
            case 500:
                chip = Chips.FIVE_HUNDRED;
                break;
            case 1000:
                chip = Chips.ONE_K;
                break;
            case 5000:
                chip = Chips.FIVE_K;
                break;
            case 10000:
                chip = Chips.TEN_K;
                break;
            case 20000:
                chip = Chips.TWENTY_K;
                break;
            case 100000:
                chip = Chips.HUNDREN_K;
                break;
            default:
                chip = Chips.HUNDRED;
                break;
        }
        return chip;
    }
}
