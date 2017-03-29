package org.winning.blackjack.repository.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import org.joda.time.LocalDateTime;
import org.winning.blackjack.deserialize.LocalDateTimeSerializer;
import org.winning.blackjack.entity.Card;
import org.winning.blackjack.entity.Result;

import java.util.List;

import lombok.Data;

@Data
public class PlayerData {

    private int id;
    private int gameId;
    private int handsId;
    private String playerName;
    private Result status;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime time;

    // currently we are only allowing two split, so there are list two at most
    private List<Card>[] allCards;
    private List<Card> dealerCards;

}
