package components.org.winning.blackjack;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.bson.Transformer;
import org.junit.Before;
import org.junit.Test;
import org.winning.blackjack.configurations.BJConfiguration;
import org.winning.blackjack.configurations.BJMongoDbConfiguration;
import org.winning.blackjack.entity.Card;
import org.winning.blackjack.entity.Color;
import org.winning.blackjack.entity.Result;
import org.winning.blackjack.repository.GamesI;
import org.winning.blackjack.repository.entity.PlayerData;
import org.winning.blackjack.repository.imp.GamesImp;
import org.winning.blackjack.repository.mongo.connector.MongoDBDriver;
import org.winning.blackjack.repository.mongo.serializer.PlayerDataDecoder;
import org.winning.blackjack.repository.mongo.serializer.PlayerDataEncoder;

import java.util.ArrayList;
import java.util.List;

public class GamesITest {

    private MongoDBDriver dbDriver;

    private BJConfiguration configuration;

    private GamesI gamesI;

    private Transformer playerDataEncoder;

    private Transformer playerDataDecoder;

    @Before
    public void setUp() throws Exception {

        playerDataEncoder = new PlayerDataEncoder();
        playerDataDecoder = new PlayerDataDecoder();

        final BJMongoDbConfiguration bjMongoDbConfiguration =
                new BJMongoDbConfiguration("localhost", 27017, "DBName");
        configuration = new BJConfiguration();
        configuration.setBJMongoDbConfiguration(bjMongoDbConfiguration);

        gamesI = new GamesImp(configuration, playerDataEncoder, playerDataDecoder);
    }

    @Test
    public void save_query_delete() throws Exception {

        //query then get nothing

        // insert some data
        final PlayerData data = createPlayerData();
        gamesI.savePlayerData(data);

        //query data
        gamesI.getAllPlayerData().stream().forEach(p -> {

            if (p.getPlayerName().equals("player1")) {
                assertTrue(true);
            } else if (p.getHandsId() == 1) {
                assertTrue(true);
            } else if (p.getGameId() == 1) {
                assertTrue(true);
            } else if (p.getStatus().equals(Result.LOST.name())) {
                assertTrue(true);
            } else if (p.getAllCards().length > 0) {
                p.getAllCards()[0].stream().filter(c -> c.getName().equals("ten"))
                        .forEach(c -> assertTrue(c.getColor().equals(Color.SPADE)));
                p.getAllCards()[0].stream().filter(c -> c.getName().equals("J"))
                        .forEach(c -> assertTrue(c.getColor().equals(Color.DIAMOND)));
            } else {
                fail();
            }
        });

        //delete all data
        gamesI.getAllPlayerData().forEach(p -> gamesI.deletePlayerData(p));
        final List l = gamesI.getAllPlayerData();
        assertTrue(l.isEmpty());
    }

    private PlayerData createPlayerData() {
        final PlayerData data = new PlayerData();
        data.setGameId(1);
        data.setHandsId(1);
        data.setPlayerName("player1");
        data.setStatus(Result.LOST);

        Card card_1 = new Card(Color.SPADE, "ten");
        Card card_2 = new Card(Color.DIAMOND, "J");
        List<Card> list1 = new ArrayList<>();
        list1.add(card_1);
        list1.add(card_2);

        Card card = new Card(Color.SPADE, "ten");
        Card card2 = new Card(Color.DIAMOND, "A");
        List<Card> list = new ArrayList<>();
        list.add(card);
        list.add(card2);

        List<Card>[] array = new List[1];
        array[0] = list;

        data.setAllCards(array);
        data.setDealerCards(list1);
        return data;
    }

    @Test
    public void savePlayerActionData() throws Exception {

    }
}

