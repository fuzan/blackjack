package components.org.winning.blackjack;

import org.bson.Transformer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.winning.blackjack.configurations.BJConfiguration;
import org.winning.blackjack.repository.GamesI;
import org.winning.blackjack.repository.imp.GamesImp;
import org.winning.blackjack.repository.mongo.connector.MongoDBDriver;
import org.winning.blackjack.repository.mongo.serializer.PlayerDataDecoder;
import org.winning.blackjack.repository.mongo.serializer.PlayerDataEncoder;

public class MongoDBIntegrationTests {

    final String ip = "localhost";
    final int port = 27017;
    final String dbName = "game";

    private MongoDBDriver dbDriver;

    private GamesI gamesI;

    private BJConfiguration configuration;

    private Transformer encoder;
    private Transformer decoder;

    @Before
    public void setUp() throws Exception {
        dbDriver = new MongoDBDriver(ip, port, dbName);
        configuration = new BJConfiguration();
        encoder = new PlayerDataEncoder();
        decoder = new PlayerDataDecoder();
        gamesI = new GamesImp(configuration, encoder, decoder);
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
