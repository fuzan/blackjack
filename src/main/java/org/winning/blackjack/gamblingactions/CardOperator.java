package org.winning.blackjack.gamblingactions;

import org.winning.blackjack.entity.Card;
import org.winning.blackjack.entity.Color;
import org.winning.blackjack.entity.Deck;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class CardOperator {

    private final static Random index = new Random();

    private CardOperator() {

    }

    private static List<Deck> buildDeck(int number) {
        List<Deck> decks = new LinkedList<>();
        for (int i = 0; i < number; i++) {
            Deck deck = new Deck(i);
            deck.setCards(create52Cards(i));
            decks.add(deck);
        }
        return decks;
    }

    public static List<Card> combineAllCards(int number){
        List<Deck> decks = buildDeck(number);
        List<Card> cards = new LinkedList<>();
        if( decks != null ){
            decks.stream().forEach( d -> cards.addAll(d.getCards()) );
        }
        return cards;
    }

    public static int generateRandomCard(int cardNumber){
        return index.nextInt(cardNumber) + 1;
    }

    public static List<Card> create52Cards(int deckId) {
        List<Card> deck = new LinkedList<>();
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

        return deck;
    }

}
