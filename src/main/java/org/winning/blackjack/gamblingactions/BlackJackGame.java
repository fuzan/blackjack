package org.winning.blackjack.gamblingactions;

import org.winning.blackjack.Deck;
import org.winning.blackjack.people.Dealer;
import org.winning.blackjack.people.Player;

import java.util.List;

public class BlackJackGame {

    private Dealer dealer;
    private List<Player> players;
    private List<Deck> decks;

    public BlackJackGame(Dealer dealer, List<Player> players, List<Deck> decks) {
        this.dealer = dealer;
        this.players = players;
        this.decks = decks;
    }

    public List<Deck> getDecks() {
        return decks;
    }

    public void setDecks(List<Deck> decks) {
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

    // init deck
    //deal two cards to player and dealer
    public void begin(){

    }

    //
    public void askPlayer(){
        //hit

        //double

        //stand

        //split
    }

    public void dealerTurn(){
        //hit to soft17

        //judge
    }

    private void judge(){

    }

}
