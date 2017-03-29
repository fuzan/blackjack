package org.winning.blackjack.logging;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.winning.blackjack.repository.entity.PlayerData;

import java.io.IOException;
import java.net.UnknownHostException;

public interface ElasticClient {

    Client getClient() throws UnknownHostException;

    boolean saveDocument(PlayerData data) throws IOException;

    void updateDocument();

    boolean deleteDocument(String id) throws UnknownHostException;

    void createAllIndex();

    void deleteOldIndex();

    void closeConnection(TransportClient client);
}
