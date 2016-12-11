package org.winning.blackjack.entity;

import java.util.LinkedList;
import java.util.List;

@SuppressWarnings({"PMD.ShortClassName"})
public class Deck {

    private int deckId;
    private List<Card> cards = new LinkedList<>();

    public Deck(int deckId) {
        this.deckId = deckId;
    }

    public int getDeckId() {
        return deckId;
    }

    public void setDeckId(int deckId) {
        this.deckId = deckId;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

}
