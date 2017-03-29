package org.winning.blackjack.logging;

import static java.util.Optional.ofNullable;
import static org.elasticsearch.common.settings.Settings.settingsBuilder;
import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingResponse;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.winning.blackjack.logging.mapper.ElasticReservedKeyWords;
import org.winning.blackjack.logging.mapper.ElsasticObjectPlayerDataMapper;
import org.winning.blackjack.repository.entity.PlayerData;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

public class ElasticClientImp implements ElasticClient {

    public static final String TYPE = "actions";
    private static final String PATH_HOME = "path.home";
    private static final String PATH_HOME_VALUE = "/usr/local/Cellar/elasticsearch@2.4/2.4.4_1/";
    private static final String CLUSTER_NAME_KEY = "cluster.name";
    private static final String CLUSTER_NAME_VALUE = "elasticsearch_zafu";
    private static final String INDEX = "user";
    private static final String LOCALHOST = "localhost";
    private static final int PORT = 9300;
    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    public ElasticClientImp() {
    }

    @Override
    public Client getClient() throws UnknownHostException {
        final Settings settings = settingsBuilder()
                .put(CLUSTER_NAME_KEY, CLUSTER_NAME_VALUE)
                .put(PATH_HOME, PATH_HOME_VALUE)
                .build();
        return createClient(settings);
    }

    private TransportClient createClient(Settings settings) {

        TransportClient client = null;
        try {
            TransportClient transportClient = new TransportClient.Builder().settings(settings).build();
            transportClient = transportClient
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(LOCALHOST), PORT));
            if (transportClient.connectedNodes().size() == 0) {
                System.out.println(
                        "There are no active nodes available for the transport, it will be automatically added once nodes are live!");
            }
            client = transportClient;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return client;
    }

    public void addNewNode(String name, TransportClient transportClient) {
        try {
            transportClient.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(name), PORT));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public void removeNode(String name, TransportClient transportClient) {
        try {
            transportClient.removeTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(name), PORT));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean saveDocument(PlayerData data) throws IOException {

        final PutMappingResponse mappingResponse = getPutMappingResponse();
        final IndexResponse response = getIndexResponse(data, data.getId());
        return mappingResponse.isAcknowledged() && response.isCreated();
    }

    private BulkResponse bulkOperation(List<IndexRequestBuilder> requests) throws UnknownHostException {
        final BulkRequestBuilder bulkRequest = getClient().prepareBulk();
        ofNullable(requests).ifPresent(r -> r.stream().forEach(re -> bulkRequest.add(re)));
        final BulkResponse bulkResponse = bulkRequest.execute().actionGet();

        ofNullable(bulkResponse).filter(b -> b.hasFailures())
                .ifPresent(r -> System.out.println("bulk operation indexing has failures:" + r.buildFailureMessage()));
        return bulkResponse;
    }

    @Override
    public void updateDocument() {

    }

    @Override
    public boolean deleteDocument(String id) throws UnknownHostException {
        final DeleteResponse response = getClient().prepareDelete(INDEX, TYPE, id).get();
        return response.isFound();
    }

    @Override
    public void createAllIndex() {
        try {
            createGivenIndex();
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public void deleteOldIndex() {
        try {
            if (isIndexExists()) {
                cleanupExistingOldIndex();
            }
        } catch (UnknownHostException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public void closeConnection(TransportClient client) {
        ofNullable(client).ifPresent(c -> client.close());
    }

    private void createGivenIndex() throws IOException {
        final CreateIndexRequestBuilder createIndexRequestBuilder =
                getClient().admin().indices().prepareCreate(INDEX).setSettings(getSettingForIndex());
        createIndexRequestBuilder.execute().actionGet();
        LOGGER.debug("Index {} created! ", INDEX);
    }

    private Settings getSettingForIndex() throws IOException {
        LOGGER.debug("Generating settings for index: {}", INDEX);
        final Settings settings = settingsBuilder().loadFromSource(
                jsonBuilder().startObject().field(ElasticReservedKeyWords.INDEX_MAPPER_DYNAMIC.getText(), false)
                        .endObject().string()).build();

        LOGGER.debug("Generated settings for index {} is: {}", new Object[]{INDEX, settings.getAsMap()});
        return settings;
    }

    private PutMappingResponse getPutMappingResponse() throws IOException {
        PutMappingResponse mappingResponse = getClient().admin().indices().preparePutMapping(INDEX)
                .setType(TYPE).setSource(ElsasticObjectPlayerDataMapper.addPlayerDataTypeMapping()).get();
        LOGGER.info("mapping response is {}.", mappingResponse.isAcknowledged());
        return mappingResponse;
    }

    private IndexResponse getIndexResponse(PlayerData data, Integer id) throws IOException {
        final IndexRequestBuilder indexRequestBuilder =
                getClient().prepareIndex(INDEX, TYPE, id.toString());
        indexRequestBuilder.setSource(ElsasticObjectPlayerDataMapper.getXContentBuilderForPlayerData(data));
        final IndexResponse response = indexRequestBuilder.get();
        LOGGER.info("create index response is {}.", response.isCreated());
        return response;
    }

    private void cleanupExistingOldIndex() throws UnknownHostException {
        final DeleteIndexResponse stateResponse =
                getClient().admin().indices().prepareDelete(INDEX).execute().actionGet();
        LOGGER.debug("Old index {} removed successfully!", stateResponse.isAcknowledged());

    }

    private boolean isIndexExists() throws UnknownHostException {
        return getClient().admin().indices().prepareExists(INDEX).get().isExists();
    }
}
