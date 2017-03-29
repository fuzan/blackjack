package components.org.winning.blackjack;

import static org.junit.Assert.assertEquals;

import org.joda.time.LocalDateTime;
import org.junit.Before;
import org.junit.Test;
import org.winning.blackjack.entity.Card;
import org.winning.blackjack.entity.Color;
import org.winning.blackjack.entity.Result;
import org.winning.blackjack.logging.ElasticClient;
import org.winning.blackjack.logging.ElasticClientImp;
import org.winning.blackjack.logging.ElasticLogging;
import org.winning.blackjack.logging.ElasticLoggingImp;
import org.winning.blackjack.repository.entity.PlayerData;
import org.winning.blackjack.util.card.RandomIntegerUtil;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class ElasticLoggingTests {

    private ElasticClient client;
    private ElasticLogging elasticLogging;

    @Before
    public void setUp() {
        client = new ElasticClientImp();
        elasticLogging = new ElasticLoggingImp(client);
    }

    @Test
    public void saveDocument() {
        //client.deleteOldIndex();
        //client.createAllIndex();

        final Integer id = RandomIntegerUtil.getNextInteger(0,9999);
        final PlayerData data = constructPlayer(id);
        elasticLogging.loggingPlayerData(data);
    }

    @Test
    public void updateDocumentType() {
        //elasticLogging.loggingPlayerData(constructPlayer());
    }

    @Test
    public void updateDocument() {

    }

    @Test
    public void deleteDocument() throws UnknownHostException {

        final Integer id = RandomIntegerUtil.getNextInteger(0,9999);
        final PlayerData data = constructPlayer(id);
        elasticLogging.loggingPlayerData(data);

        boolean succ = client.deleteDocument(id.toString());
        assertEquals(succ, true);
    }

    private PlayerData constructPlayer(Integer id) {
        PlayerData data = new PlayerData();
        data.setStatus(Result.WIN);
        data.setId(id);

        Card card1 = new Card(Color.DIAMOND, "A");
        List list = new ArrayList<Card>();
        list.add(card1);
        List[] allCards = new List[2];
        allCards[0] = list;

        data.setAllCards(allCards);
        data.setDealerCards(list);
        data.setGameId(3);
        data.setHandsId(2);
        data.setPlayerName("player1");
        data.setTime(LocalDateTime.now());

        return data;
    }
}
