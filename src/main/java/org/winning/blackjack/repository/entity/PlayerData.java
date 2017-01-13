package org.winning.blackjack.repository.entity;

import org.joda.time.LocalDateTime;
import org.winning.blackjack.entity.Card;
import org.winning.blackjack.entity.Result;

import java.util.List;

public class PlayerData {

    private int gameId;
    private int handsId;
    private String playerName;
    private Result status;
    private LocalDateTime time;

    // currently we are only allowing two split, so there are list two at most
    private List<Card>[] allCards;
    private List<Card> dealerCards;

    public Result getStatus() {
        return status;
    }

    public void setStatus(Result status) {
        this.status = status;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getHandsId() {
        return handsId;
    }

    public void setHandsId(int handsId) {
        this.handsId = handsId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public List<Card>[] getAllCards() {
        return allCards;
    }

    public void setAllCards(List<Card>[] allCards) {
        this.allCards = allCards;
    }

    public List<Card> getDealerCards() {
        return dealerCards;
    }

    public void setDealerCards(List<Card> dealerCards) {
        this.dealerCards = dealerCards;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
