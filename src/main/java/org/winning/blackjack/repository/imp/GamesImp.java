package org.winning.blackjack.repository.imp;

import static org.winning.blackjack.repository.mongoConnector.PlayerSchema.ACTION;
import static org.winning.blackjack.repository.mongoConnector.PlayerSchema.CARD_COLOR;
import static org.winning.blackjack.repository.mongoConnector.PlayerSchema.CARD_NAME;
import static org.winning.blackjack.repository.mongoConnector.PlayerSchema.CARD_VALUE;
import static org.winning.blackjack.repository.mongoConnector.PlayerSchema.DEALER_CARDS;
import static org.winning.blackjack.repository.mongoConnector.PlayerSchema.GAME_ID;
import static org.winning.blackjack.repository.mongoConnector.PlayerSchema.HANDS_ID;
import static org.winning.blackjack.repository.mongoConnector.PlayerSchema.PLAYER;
import static org.winning.blackjack.repository.mongoConnector.PlayerSchema.PLAYER_ACTION;
import static org.winning.blackjack.repository.mongoConnector.PlayerSchema.PLAYER_CARDS_1;
import static org.winning.blackjack.repository.mongoConnector.PlayerSchema.PLAYER_CARDS_2;
import static org.winning.blackjack.repository.mongoConnector.PlayerSchema.PLAYER_NAME;
import static org.winning.blackjack.repository.mongoConnector.PlayerSchema.RESULT;

import com.google.inject.Inject;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

import org.bson.BSON;
import org.bson.Transformer;
import org.winning.blackjack.configurations.BJConfiguration;
import org.winning.blackjack.entity.Action;
import org.winning.blackjack.entity.Card;
import org.winning.blackjack.entity.Color;
import org.winning.blackjack.entity.Result;
import org.winning.blackjack.repository.GamesI;
import org.winning.blackjack.repository.entity.PlayerActionsData;
import org.winning.blackjack.repository.entity.PlayerData;
import org.winning.blackjack.repository.mongoConnector.MongoDBDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * game object :
 * {
 * gameId : 1
 * dealer : {name:""}
 * players : [{name:"",stake:500},{name:"",stake:2000},{name:"",stake:100}]
 * }
 */
public class GamesImp implements GamesI {

    private MongoDBDriver dbDriver;
    private BJConfiguration configuration;


    @Inject
    public GamesImp(MongoDBDriver dbDriver, BJConfiguration configuration, Transformer transformer) {
        this.dbDriver = dbDriver;
        this.configuration = configuration;

        BSON.addEncodingHook(PlayerData.class, transformer);
    }

    @Override
    public List<PlayerData> getAllPlayerData() throws Exception {
        List<PlayerData> list = new ArrayList<>();
        final DBCollection collection = dbDriver.getCollection(PLAYER);

        try (final DBCursor cursor = collection.find()) {
            while (cursor.hasNext()) {
                BasicDBObject dbObject = (BasicDBObject) cursor.next();
                list.add(convertBasicObjectToPlayerData(dbObject));
            }
        }
        return list;
    }

    @Override
    public void savePlayerData(PlayerData game) throws Exception {
        final DBCollection collection = dbDriver.getCollection(PLAYER);
        final BasicDBObject data = convertPlayerDataToBasicObject(game);
        collection.insert(data);
    }

    @Override
    public void savePlayerActionData(PlayerActionsData data) throws Exception {
        final DBCollection collection = dbDriver.getCollection(PLAYER_ACTION);
        final BasicDBObject actionData = convertActionDataToBasicObject(data);
        collection.insert(actionData);
    }

    private BasicDBObject convertActionDataToBasicObject(PlayerActionsData data) {
        final BasicDBObject object = new BasicDBObject(GAME_ID, data.getGameId());
        object.append(HANDS_ID, data.getHandsId());
        object.append(PLAYER_NAME, appendActionsToObject(data.getPlayerActions()));
        return object;
    }

    private BasicDBObject convertPlayerDataToBasicObject(PlayerData game) {
        final BasicDBObject object = new BasicDBObject(GAME_ID, game.getGameId());

        object.append(HANDS_ID, game.getHandsId());
        object.append(PLAYER_NAME, game.getPlayerName());
        object.append(RESULT, game.getStatus().name());
        object.append(DEALER_CARDS, appendCardsToObject(game.getDealerCards()));
        object.append(PLAYER_CARDS_1, appendCardsToObject(game.getAllCards()[0]));
        if (game.getAllCards().length > 1) {
            object.append(PLAYER_CARDS_2, appendCardsToObject(game.getAllCards()[1]));
        }
        return object;
    }

    private PlayerData convertBasicObjectToPlayerData(BasicDBObject object) {

        final PlayerData data = new PlayerData();

        setProperties(object, GAME_ID, f -> data.setGameId(Integer.parseInt(f.toString())));
        setProperties(object, HANDS_ID, f -> data.setHandsId(Integer.parseInt(f.toString())));
        setProperties(object, RESULT, f -> data.setStatus(Result.valueOf(f.toString())));
        setProperties(object, PLAYER_NAME, f -> data.setPlayerName(f.toString()));

        setProperties(object, DEALER_CARDS, f -> data.setDealerCards(setListProperties((BasicDBList) f)));
        setProperties(object, PLAYER_CARDS_1, f -> data.setDealerCards(setListProperties((BasicDBList) f)));
        setProperties(object, PLAYER_CARDS_2, f -> data.setDealerCards(setListProperties((BasicDBList) f)));

        return data;
    }

    private List<Card> setListProperties(BasicDBList list) {
        final List<Card> list1 = new ArrayList<>();
        list.forEach(o -> {
            final String card_name = setPropertiesFunction((BasicDBObject) o, CARD_NAME, v -> v);
            final String card_color = setPropertiesFunction((BasicDBObject) o, CARD_COLOR, v -> v);
            final Card card = new Card(Color.valueOf(card_color), card_name);
            list1.add(card);
        });
        return list1;
    }

    private BasicDBList appendCardsToObject(List<Card> list) {
        final BasicDBList dbl = new BasicDBList();
        list.forEach(p -> dbl.add(new BasicDBObject(CARD_NAME, p.getName()).append(CARD_COLOR, p.getColor().name())
                                          .append(CARD_VALUE, p.getValue())
        ));
        return dbl;
    }

    private BasicDBList appendActionsToObject(List<Action> list) {
        final BasicDBList dbl = new BasicDBList();
        list.forEach(p -> dbl.add(new BasicDBObject(ACTION, p.name())));
        return dbl;
    }

    private String setPropertiesFunction(BasicDBObject object, String key, Function mapFunction) {
        return (String) object.computeIfPresent(key, (k, v) -> mapFunction.apply(v));
    }

    private void setProperties(BasicDBObject object, String key, Consumer mapFunction) {
        object.computeIfPresent(key, (k, v) -> {
            mapFunction.accept(v);
            return 0;
        });
    }
}
