package org.winning.blackjack.repository.mongo.serializer;

public interface PlayerSchema {

    String PLAYER = "PlayerData";
    String PLAYER_ACTION = "PlayerActionData";
    String GAME_ID = "gameId";
    String HANDS_ID = "handsId";
    String PLAYER_NAME = "playerName";
    String RESULT = "result";
    String DEALER_CARDS = "dealerCards";
    String CARD_NAME = "cardName";
    String CARD_COLOR = "cardColor";
    String CARD_VALUE = "cardValue";
    String PLAYER_CARDS_1 = "playerCards_1";
    String PLAYER_CARDS_2 = "playerCards_2";
    String ACTION = "action";
}
