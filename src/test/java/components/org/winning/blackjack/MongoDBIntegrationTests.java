package components.org.winning.blackjack;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.winning.blackjack.configurations.BJConfiguration;
import org.winning.blackjack.deserialize.JsonDeseria;
import org.winning.blackjack.deserialize.ObjectdeserializeInter;
import org.winning.blackjack.repository.GamesI;
import org.winning.blackjack.repository.imp.GamesImp;
import org.winning.blackjack.repository.mongoConnector.MongoDBDriver;

public class MongoDBIntegrationTests {

    final String ip = "localhost";
    final int port = 27017;
    final String dbName = "game";

    private MongoDBDriver dbDriver;

    private GamesI gamesI;

    private BJConfiguration configuration;

    private ObjectdeserializeInter<String> serializer;

    @Before
    public void setUp() throws Exception {
        dbDriver = new MongoDBDriver(ip, port, dbName);
        configuration = new BJConfiguration();
        serializer = new JsonDeseria(new ObjectMapper());
        gamesI = new GamesImp(dbDriver, configuration);
    }

    @Test
    public void mongoDBConnection() {
        Assert.assertTrue(dbDriver.getAllMongoDBs().isPresent());
    }

    public void mongoDBInsertData() {

    }

    public void mongoDBQuery() {

    }


}
