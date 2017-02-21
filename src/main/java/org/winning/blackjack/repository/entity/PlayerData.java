package org.winning.blackjack.repository.entity;

import org.joda.time.LocalDateTime;
import org.winning.blackjack.entity.Card;
import org.winning.blackjack.entity.Result;

import java.util.List;

@lombok.Data
@lombok.ToString
public class PlayerData {

    private int gameId;
    private int handsId;
    private String playerName;
    private Result status;
    private LocalDateTime time;

    // currently we are only allowing two split, so there are list two at most
    private List<Card>[] allCards;
    private List<Card> dealerCards;
}
