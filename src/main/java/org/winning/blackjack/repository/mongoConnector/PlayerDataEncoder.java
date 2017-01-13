package org.winning.blackjack.repository.mongoConnector;

import static org.winning.blackjack.repository.mongoConnector.PlayerSchema.CARD_COLOR;
import static org.winning.blackjack.repository.mongoConnector.PlayerSchema.CARD_NAME;
import static org.winning.blackjack.repository.mongoConnector.PlayerSchema.CARD_VALUE;
import static org.winning.blackjack.repository.mongoConnector.PlayerSchema.DEALER_CARDS;
import static org.winning.blackjack.repository.mongoConnector.PlayerSchema.GAME_ID;
import static org.winning.blackjack.repository.mongoConnector.PlayerSchema.HANDS_ID;
import static org.winning.blackjack.repository.mongoConnector.PlayerSchema.PLAYER_CARDS_1;
import static org.winning.blackjack.repository.mongoConnector.PlayerSchema.PLAYER_CARDS_2;
import static org.winning.blackjack.repository.mongoConnector.PlayerSchema.PLAYER_NAME;

import org.bson.BSONObject;
import org.bson.BasicBSONObject;
import org.bson.Transformer;
import org.bson.types.BasicBSONList;
import org.winning.blackjack.entity.Card;
import org.winning.blackjack.repository.entity.PlayerData;

import java.util.List;

/**
 * input PlayerData object
 * output BSON
 */
public class PlayerDataEncoder implements Transformer {
    @Override
    public Object transform(Object objectToTransform) {
        if (objectToTransform != null && objectToTransform instanceof PlayerData) {
            final BSONObject basicBSONObject = new BasicBSONObject();
            basicBSONObject.put(PLAYER_NAME, ((PlayerData) objectToTransform).getPlayerName());
            basicBSONObject.put(HANDS_ID, ((PlayerData) objectToTransform).getHandsId());
            basicBSONObject.put(GAME_ID, ((PlayerData) objectToTransform).getGameId());
            basicBSONObject.put(PLAYER_CARDS_1, convertListToBSON(((PlayerData) objectToTransform).getAllCards()[0]));
            if (((PlayerData) objectToTransform).getAllCards().length > 1) {
                basicBSONObject
                        .put(PLAYER_CARDS_2, convertListToBSON(((PlayerData) objectToTransform).getAllCards()[1]));
            }
            basicBSONObject.put(DEALER_CARDS, convertListToBSON(((PlayerData) objectToTransform).getDealerCards()));
            return basicBSONObject;
        }
        return null;
    }

    private BasicBSONList convertListToBSON(List<Card> cards) {
        final BasicBSONList list = new BasicBSONList();
        cards.forEach(c -> {
            final BSONObject object = new BasicBSONObject();
            object.put(CARD_NAME, c.getName());
            object.put(CARD_COLOR, c.getColor().name());
            object.put(CARD_VALUE, c.getValue());
            list.add(object);
        });
        return list;
    }
}
