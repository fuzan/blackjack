package org.winning.blackjack.repository;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.winning.blackjack.CardValueUtil.BJLogger;
import org.winning.blackjack.configurations.BJConfiguration;
import org.winning.blackjack.deserialize.ObjectdeserializeInter;
import org.winning.blackjack.gamblingactions.BlackJackGame;
import org.winning.blackjack.people.Dealer;
import org.winning.blackjack.people.Player;
import org.winning.blackjack.repository.mongo.connector.MongoDBDriver;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class GamesTests {

    @Mock
    private BJConfiguration configuration;
    @Mock
    private ObjectdeserializeInter<String> serializer;
    @Mock
    private MongoDBDriver driver;
    @Mock
    private DBCollection collection;
    @Mock
    private BJLogger logger;
    @Mock
    private DBCursor cursor;

    @Before
    public void setUp() throws Exception {
        final String connection = "localhost";
        final int port = 21021;

        MockitoAnnotations.initMocks(this);
        when(configuration.getDeckNumber()).thenReturn(1);
        //when(configuration.getBJMongoDbConfiguration()).thenReturn(new BJMongoDbConfiguration(connection, port));
        //when(serializer.convertDealerTo(any(Dealer.class))).thenReturn("{'dealer':'dealer1'}");
        when(driver.getCollection(anyString())).thenReturn(collection);
        when(collection.find()).thenReturn(cursor);
        when(cursor.hasNext()).thenReturn(false);
    }

    @Test
    public void getAllGames() throws Exception {
        //GamesI gamesI = new GamesImp(configuration, serializer, driver);
        //List<BlackJackGame> games = gamesI.getAllPlayerData();
        verify(driver.getCollection("game").find(), times(1)).hasNext();
        //Assert.assertTrue(games.size() == 0);
    }

    @Test
    public void saveGameData() throws Exception {
        Dealer dealer = new Dealer("dealer");
        Player player = new Player("player");
        List<Player> list = new ArrayList<>();
        list.add(player);

        BlackJackGame game = new BlackJackGame(dealer, list, configuration.getDeckNumber(), logger);

//        GamesI gamesI = new GamesImp(configuration, serializer, driver);
//        gamesI.savePlayerData(game);
        verify(driver.getCollection("game"), times(1)).insert(any(BasicDBObject.class));
    }
}