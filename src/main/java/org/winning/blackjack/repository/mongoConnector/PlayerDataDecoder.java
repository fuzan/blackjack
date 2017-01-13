package org.winning.blackjack.repository.mongoConnector;

import static org.winning.blackjack.repository.mongoConnector.PlayerSchema.*;

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
            final BasicDBList dealer_cards = (BasicDBList) ((BSONObject) objectToTransform).get(DEALER_CARDS);
            final Object result = ((BSONObject) objectToTransform).get(RESULT).toString();
            final String hands_id = ((BSONObject) objectToTransform).get(HANDS_ID).toString();
            final String game_id = ((BSONObject) objectToTransform).get(GAME_ID).toString();
            final BasicDBList player_cards_1 = (BasicDBList) ((BSONObject) objectToTransform).get(PLAYER_CARDS_1);
            final BasicDBList player_cards_2 = (BasicDBList) ((BSONObject) objectToTransform).get(PLAYER_CARDS_2);

            data.setTime(new LocalDateTime());
            data.setPlayerName(player_name);
            data.setDealerCards(setListProperties(dealer_cards));
            data.setStatus(Result.valueOf(result.toString()));
            data.setHandsId(Integer.parseInt(hands_id));
            data.setGameId(Integer.parseInt(game_id));
            final List[] card_array = new List[2];
            card_array[0] = setListProperties(player_cards_1);
            card_array[1] = setListProperties(player_cards_2);
            data.setAllCards(card_array);
            return data;
        }
        return null;
    }

    private List<Card> setListProperties(BasicDBList list) {
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
