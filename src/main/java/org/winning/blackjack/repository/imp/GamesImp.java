package org.winning.blackjack.repository.imp;

import static org.winning.blackjack.repository.mongo.serializer.PlayerSchema.ACTION;
import static org.winning.blackjack.repository.mongo.serializer.PlayerSchema.GAME_ID;
import static org.winning.blackjack.repository.mongo.serializer.PlayerSchema.HANDS_ID;
import static org.winning.blackjack.repository.mongo.serializer.PlayerSchema.PLAYER;
import static org.winning.blackjack.repository.mongo.serializer.PlayerSchema.PLAYER_ACTION;
import static org.winning.blackjack.repository.mongo.serializer.PlayerSchema.PLAYER_NAME;

import com.google.inject.Inject;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import org.bson.BSON;
import org.bson.Transformer;
import org.winning.blackjack.configurations.BJConfiguration;
import org.winning.blackjack.entity.Action;
import org.winning.blackjack.repository.GamesI;
import org.winning.blackjack.repository.entity.PlayerActionsData;
import org.winning.blackjack.repository.entity.PlayerData;
import org.winning.blackjack.repository.mongo.connector.MongoDBDriver;

import java.util.ArrayList;
import java.util.List;

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
    private Transformer decoder;

    @Inject
    public GamesImp(BJConfiguration configuration, Transformer encoder, Transformer decoder) {
        this.dbDriver = new MongoDBDriver(configuration.getBJMongoDbConfiguration().getConnection(),
                                          configuration.getBJMongoDbConfiguration().getPort(),
                                          configuration.getBJMongoDbConfiguration().getDbName());

        BSON.addEncodingHook(PlayerData.class, encoder);
        this.decoder = decoder;
    }

    @Override
    public List<PlayerData> getAllPlayerData() {
        List<PlayerData> list = new ArrayList<>();
        final DBCollection collection = dbDriver.getCollection(PLAYER);

        try (final DBCursor cursor = collection.find()) {
            while (cursor.hasNext()) {
                BasicDBObject dbObject = (BasicDBObject) cursor.next();
                PlayerData playerData = (PlayerData) decoder.transform(dbObject);
                list.add(playerData);
            }
        }
        return list;
    }

    @Override
    public void savePlayerData(PlayerData game) {
        final DBCollection collection = dbDriver.getCollection(PLAYER);
        final BasicDBObject data = (BasicDBObject) BSON.applyEncodingHooks(game);
        collection.insert(data);
    }

    @Override
    public void savePlayerActionData(PlayerActionsData data) {
        final DBCollection collection = dbDriver.getCollection(PLAYER_ACTION);
        final BasicDBObject actionData = convertActionDataToBasicObject(data);
        collection.insert(actionData);
    }

    @Override
    public void deletePlayerData(PlayerData data) {
        final DBCollection collection = dbDriver.getCollection(PLAYER_ACTION);
        DBObject dbObject = (DBObject) BSON.applyEncodingHooks(data);
        collection.remove(dbObject);
    }

    private BasicDBObject convertActionDataToBasicObject(PlayerActionsData data) {
        final BasicDBObject object = new BasicDBObject(GAME_ID, data.getGameId());
        object.append(HANDS_ID, data.getHandsId());
        object.append(PLAYER_NAME, appendActionsToObject(data.getPlayerActions()));
        return object;
    }

    private BasicDBList appendActionsToObject(List<Action> list) {
        final BasicDBList dbl = new BasicDBList();
        list.forEach(p -> dbl.add(new BasicDBObject(ACTION, p.name())));
        return dbl;
    }
}
