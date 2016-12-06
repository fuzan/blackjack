package org.winning.blackjack;

import org.winning.blackjack.people.Dealer;
import org.winning.blackjack.people.Player;

import java.util.List;
import java.util.Map;

@SuppressWarnings({"PMD.ShortClassName"})
public class Game {

    private Dealer dealer;
    private List<Player> players;
    private List<Deck> decks;

    private Map<String, Result> playerResults;

    public Game(Dealer dealer, List<Deck> decks) {
        this.dealer = dealer;
        this.decks = decks;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public void setDealer(Dealer dealer) {
        this.dealer = dealer;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Deck> getDecks() {
        return decks;
    }

    public void setDecks(List<Deck> decks) {
        this.decks = decks;
    }

    public Map<String, Result> getPlayerResults() {
        return playerResults;
    }

    public void setPlayerResults(Map<String, Result> playerResults) {
        this.playerResults = playerResults;
    }
}
