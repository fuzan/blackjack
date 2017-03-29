package org.winning.blackjack.repository.mongo.serializer;

import static org.winning.blackjack.repository.mongo.serializer.PlayerSchema.CARD_COLOR;
import static org.winning.blackjack.repository.mongo.serializer.PlayerSchema.CARD_NAME;
import static org.winning.blackjack.repository.mongo.serializer.PlayerSchema.DEALER_CARDS;
import static org.winning.blackjack.repository.mongo.serializer.PlayerSchema.GAME_ID;
import static org.winning.blackjack.repository.mongo.serializer.PlayerSchema.HANDS_ID;
import static org.winning.blackjack.repository.mongo.serializer.PlayerSchema.PLAYER_CARDS_1;
import static org.winning.blackjack.repository.mongo.serializer.PlayerSchema.PLAYER_CARDS_2;
import static org.winning.blackjack.repository.mongo.serializer.PlayerSchema.PLAYER_NAME;
import static org.winning.blackjack.repository.mongo.serializer.PlayerSchema.RESULT;

import com.mongodb.BasicDBList;

import org.bson.BSONObject;
import org.bson.Transformer;
import org.joda.time.LocalDateTime;
import org.winning.blackjack.entity.Card;
import org.winning.blackjack.entity.Color;
import org.winning.blackjack.entity.Result;
import org.winning.blackjack.repository.entity.PlayerData;

import java.util.ArrayList;
import java.util.List;

/**
 * input BSON
 * output PlayerData
 */
public class PlayerDataDecoder implements Transformer {

    @Override
    public Object transform(Object objectToTransform) {

        if (objectToTransform != null && objectToTransform instanceof BSONObject) {
            final PlayerData data = new PlayerData();

            final String player_name = ((BSONObject) objectToTransform).get(PLAYER_NAME).toString();
            final BasicDBList dealer_cards = (BasicDBList) ((BSONObject) objectToTransform).get(
                    DEALER_CARDS);
            final Object result = ((BSONObject) objectToTransform).get(RESULT).toString();
            final String hands_id = ((BSONObject) objectToTransform).get(HANDS_ID).toString();
            final String game_id = ((BSONObject) objectToTransform).get(GAME_ID).toString();
            final BasicDBList player_cards_1 = (BasicDBList) ((BSONObject) objectToTransform).get(
                    PLAYER_CARDS_1);
            final BasicDBList player_cards_2 = (BasicDBList) ((BSONObject) objectToTransform).get(
                    PLAYER_CARDS_2);

            data.setTime(new LocalDateTime());
            data.setPlayerName(player_name);
            data.setDealerCards(convertDBListToJavaList(dealer_cards));
            data.setStatus(Result.valueOf(result.toString()));
            data.setHandsId(Integer.parseInt(hands_id));
            data.setGameId(Integer.parseInt(game_id));
            final List[] card_array =
                    {convertDBListToJavaList(player_cards_1), convertDBListToJavaList(player_cards_2)};
            data.setAllCards(card_array);
            return data;
        }
        return null;
    }

    private List<Card> convertDBListToJavaList(BasicDBList list) {
        if (list == null || list.size() == 0) {
            return null;
        }
        final List<Card> list1 = new ArrayList<>();
        list.forEach(o -> {
            final String card_name = ((BSONObject) o).get(CARD_NAME).toString();
            final String card_color = ((BSONObject) o).get(CARD_COLOR).toString();
            final Card card = new Card(Color.valueOf(card_color), card_name);
            list1.add(card);
        });
        return list1;
    }
}
