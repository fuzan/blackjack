package org.winning.blackjack;

import java.util.List;

@SuppressWarnings({"PMD.ShortClassName"})
public class Deck {

    private int deckId;
    private List<Card> deck;

    public Deck(int deckId, List<Card> deck) {
        this.deckId = deckId;
        this.deck = deck;
    }

    public int getDeckId() {
        return deckId;
    }

    public void setDeckId(int deckId) {
        this.deckId = deckId;
    }

    public List<Card> getDeck() {
        return deck;
    }

    public void setDeck(List<Card> deck) {
        this.deck = deck;
    }
}
