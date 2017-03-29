package org.winning.blackjack.logging;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.winning.blackjack.repository.entity.PlayerData;

@RunWith(MockitoJUnitRunner.class)
public class ElasticClientImpTest {

    @Mock
    private ElasticClient mockClient;
    private ElasticLogging logging;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        when(mockClient.saveDocument(any(PlayerData.class))).thenReturn(true);
        logging = new ElasticLoggingImp(mockClient);
    }

    @Test
    public void saveDocument() throws Exception {

        logging.loggingPlayerData(null);
    }

    @Test
    public void updateDocument() throws Exception {

    }

    @Test
    public void deleteDocument() throws Exception {
    }

    private PlayerData fixturePlayerData(){
        final PlayerData data = new PlayerData();
        return data;
    }
}