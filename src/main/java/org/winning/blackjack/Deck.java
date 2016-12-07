package org.winning.blackjack;

import java.util.LinkedList;
import java.util.List;

@SuppressWarnings({"PMD.ShortClassName"})
public class Deck {

    private int deckId;
    private List<Card> deck = new LinkedList<>();

    public Deck(int deckId) {
        this.deckId = deckId;
        createDecks(deckId);
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

    public void createDecks(int deckId) {
        String[] names =
                new String[]{"A", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "J", "Q",
                             "K"};

        Color[] colors = new Color[]{Color.CLUB, Color.DIAMOND, Color.HEART, Color.SPADE};

        for (String name : names) {
            for (Color color : colors) {
                final Card card = new Card(color, name);
                card.setDeckId(deckId);
                deck.add(card);
            }
        }
    }

}
