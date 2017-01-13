package components.org.winning.blackjack;

import static org.junit.Assert.assertTrue;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Before;
import org.junit.Test;
import org.winning.blackjack.configurations.BJConfiguration;
import org.winning.blackjack.configurations.BJMongoDbConfiguration;
import org.winning.blackjack.deserialize.JsonDeseria;
import org.winning.blackjack.deserialize.ObjectdeserializeInter;
import org.winning.blackjack.entity.Card;
import org.winning.blackjack.entity.Color;
import org.winning.blackjack.entity.Result;
import org.winning.blackjack.repository.GamesI;
import org.winning.blackjack.repository.entity.PlayerData;
import org.winning.blackjack.repository.imp.GamesImp;
import org.winning.blackjack.repository.mongoConnector.MongoDBDriver;

import java.util.ArrayList;
import java.util.List;

public class GamesITest {

    private MongoDBDriver dbDriver;

    private BJConfiguration configuration;

    private ObjectdeserializeInter serializer;

    private GamesI gamesI;

    @Before
    public void setUp() throws Exception {

        final BJMongoDbConfiguration bjMongoDbConfiguration =
                new BJMongoDbConfiguration("localhost", 27017, "DBName");
        configuration = new BJConfiguration();
        configuration.setBJMongoDbConfiguration(bjMongoDbConfiguration);

        dbDriver = new MongoDBDriver(configuration.getBJMongoDbConfiguration().getConnection(),
                                     configuration.getBJMongoDbConfiguration().getPort(),
                                     configuration.getBJMongoDbConfiguration().getDbName());

        serializer = new JsonDeseria(new ObjectMapper());
        gamesI = new GamesImp(dbDriver, configuration);
    }

    @Test
    public void getAllPlayerData() throws Exception {

    }

    @Test
    public void savePlayerData() throws Exception {
        final PlayerData data = createPlayerData();
        //gamesI.savePlayerData(data);

        gamesI.getAllPlayerData().stream().forEach(g -> assertTrue(g.getGameId() == 1));
        assertTrue(gamesI.getAllPlayerData().stream().filter(g -> g.getPlayerName().equals("player1")).findAny().isPresent());
        gamesI.getAllPlayerData().stream().forEach(g -> assertTrue(g.getHandsId() == 1));
        gamesI.getAllPlayerData().stream().forEach(g -> assertTrue(g.getStatus().equals(Result.LOST)));
        gamesI.getAllPlayerData().stream().forEach(g -> {
            List<Card> list = g.getDealerCards();
            list.stream().filter(c -> c.getName().equals("ten"))
                    .forEach(c -> assertTrue(c.getColor().equals(Color.SPADE)));
            list.stream().filter(c -> c.getName().equals("J"))
                    .forEach(c -> assertTrue(c.getColor().equals(Color.DIAMOND)));
        });
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

