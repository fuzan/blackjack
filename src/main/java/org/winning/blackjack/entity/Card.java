package org.winning.blackjack.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@SuppressWarnings({"PMD.ShortClassName"})
public class Card implements Serializable {

    private int value;
    private Color color;
    private String name;
    private int deckId;
    private boolean show;

    public Card(Color color, String name) {
        this.color = color;
        this.name = name;
        setValue(name);
    }

    @JsonProperty
    public int getDeckId() {
        return deckId;
    }

    public void setDeckId(int deckId) {
        this.deckId = deckId;
    }

    @JsonProperty
    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    @JsonProperty
    public int getValue() {
        return value;
    }

    private void setValue(String name) {
        switch (name) {
            case "A":
                value = 1;
                break;
            case "two":
                value = 2;
                break;
            case "three":
                value = 3;
                break;
            case "four":
                value = 4;
                break;
            case "five":
                value = 5;
                break;
            case "six":
                value = 6;
                break;
            case "seven":
                value = 7;
                break;
            case "eight":
                value = 8;
                break;
            case "nine":
                value = 9;
                break;
            case "ten":
            case "J":
            case "Q":
            case "K":
                value = 10;
                break;
            default:
                value = 0;
        }
    }

    @JsonProperty
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @JsonProperty
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Card{" +
               "value=" + value +
               ", color=" + color +
               ", name='" + name + '\'' +
               ", deckId=" + deckId +
               ", show=" + show +
               '}';
    }
}
