package org.winning.blackjack.logging.mapper;

import static java.util.Optional.ofNullable;
import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;
import static org.winning.blackjack.logging.mapper.ElasticReservedKeyWords.BOOLEAN;
import static org.winning.blackjack.logging.mapper.ElasticReservedKeyWords.DATE;
import static org.winning.blackjack.logging.mapper.ElasticReservedKeyWords.DYNAMIC;
import static org.winning.blackjack.logging.mapper.ElasticReservedKeyWords.FORMAT;
import static org.winning.blackjack.logging.mapper.ElasticReservedKeyWords.INDEX;
import static org.winning.blackjack.logging.mapper.ElasticReservedKeyWords.INTEGER;
import static org.winning.blackjack.logging.mapper.ElasticReservedKeyWords.NOT_ANALYZED;
import static org.winning.blackjack.logging.mapper.ElasticReservedKeyWords.PROPERTIES;
import static org.winning.blackjack.logging.mapper.ElasticReservedKeyWords.SEARCH_DATE_FORMAT;
import static org.winning.blackjack.logging.mapper.ElasticReservedKeyWords.STORE;
import static org.winning.blackjack.logging.mapper.ElasticReservedKeyWords.STRING;
import static org.winning.blackjack.logging.mapper.ElasticReservedKeyWords.TYPE;
import static org.winning.blackjack.logging.mapper.ElasticReservedKeyWords.YES;

import org.elasticsearch.common.xcontent.XContentBuilder;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.winning.blackjack.entity.Card;
import org.winning.blackjack.logging.ElasticClientImp;
import org.winning.blackjack.repository.entity.PlayerData;

import java.io.IOException;
import java.util.List;

public class ElsasticObjectPlayerDataMapper {

    public static final String STRICT = "strict";
    public static final String CARD_VALUE = "card_value";
    public static final String CARD_COLOR = "card_color";
    public static final String CARD_DECK = "card_deck";
    public static final String CARD_NAME = "card_name";
    private static final String PLAYER_ALL_CARDS = "player_all_cards";
    private static final String DEALER_CARDS = "dealer_cards";
    private static final String PLAYER_GAME_ID = "player_game_id";
    private static final String PLAYER_HAND_ID = "player_hand_id";
    private static final String PLAYER_NAME = "player_name";
    private static final String PLAYER_TIMESTAMP = "player_timestamp";

    private final static Logger LOGGER = LoggerFactory.getLogger("ElsasticObjectPlayerDataMapper");

    public static XContentBuilder getXContentBuilderForPlayerData(PlayerData playerData) throws IOException {
        XContentBuilder contentBuilder;

        contentBuilder = jsonBuilder().prettyPrint().startObject();
        contentBuilder.field(PLAYER_GAME_ID, playerData.getGameId())
                .field(PLAYER_HAND_ID, playerData.getHandsId())
                .field(PLAYER_NAME, playerData.getPlayerName())
                .field(PLAYER_TIMESTAMP, dateToString(playerData.getTime()));

        contentBuilder.startArray(PLAYER_ALL_CARDS);
        buildCardsArray(playerData.getAllCards()[0], contentBuilder);
        if (playerData.getAllCards().length > 1) {
            buildCardsArray(playerData.getAllCards()[1], contentBuilder);
        }
        contentBuilder.endArray();

        contentBuilder.startArray(DEALER_CARDS);
        buildCardsArray(playerData.getDealerCards(), contentBuilder);
        contentBuilder.endArray();

        contentBuilder.endObject();
        return contentBuilder;
    }

    public static XContentBuilder addPlayerDataTypeMapping() throws IOException {

        final XContentBuilder builder = jsonBuilder().prettyPrint().startObject().startObject(ElasticClientImp.TYPE);
        //disable dynamic mappings
        builder.field(DYNAMIC.getText(), STRICT);

        //add mapping
        builder.startObject(PROPERTIES.getText());
        addArrayMapping(builder, PLAYER_ALL_CARDS);
        addArrayMapping(builder, DEALER_CARDS);
        addIntegerDataMapping(builder, PLAYER_HAND_ID);
        addIntegerDataMapping(builder, PLAYER_GAME_ID);
        addTimeDataMapping(builder, PLAYER_TIMESTAMP);
        addStringDataMapping(builder, PLAYER_NAME);
        builder.endObject().endObject();

        return builder;
    }

    private static void addIntegerDataMapping(XContentBuilder builder, String intValue) throws IOException {
        builder.startObject(intValue)
                .field(TYPE.getText(), INTEGER.getText())
                .field(INDEX.getText(), NOT_ANALYZED.getText())
                .field(STORE.getText(), YES.getText())
                .endObject();
    }

    private static void addTimeDataMapping(XContentBuilder builder, String date) throws IOException {
        builder.startObject(date)
                .field(TYPE.getText(), DATE.getText())
                .field(FORMAT.getText(), SEARCH_DATE_FORMAT.getText())
                .endObject();
    }

    private static void addStringDataMapping(XContentBuilder builder, String key) throws IOException {
        builder.startObject(key)
                .field(TYPE.getText(), BOOLEAN.getText())
                .field(INDEX.getText(), NOT_ANALYZED.getText())
                .field(STORE.getText(), YES.getText())
                .endObject();
    }

    private static void addArrayMapping(XContentBuilder builder, String key) throws IOException {
        builder.startObject(key)
                .startObject(PROPERTIES.getText())
                .startObject(CARD_VALUE)
                .field(TYPE.getText(), INTEGER.getText())
                .field(STORE.getText(), YES.getText())
                .field(INDEX.getText(), NOT_ANALYZED.getText())
                .endObject()
                .startObject(CARD_COLOR)
                .field(TYPE.getText(), STRING.getText())
                .field(STORE.getText(), YES.getText())
                .field(INDEX.getText(), NOT_ANALYZED.getText())
                .endObject()
                .startObject(CARD_DECK)
                .field(TYPE.getText(), STRING.getText())
                .field(STORE.getText(), YES.getText())
                .field(INDEX.getText(), NOT_ANALYZED.getText())
                .endObject()
                .startObject(CARD_NAME)
                .field(TYPE.getText(), STRING.getText())
                .field(STORE.getText(), YES.getText())
                .field(INDEX.getText(), NOT_ANALYZED.getText())
                .endObject()
                .endObject().endObject();
    }

    private static void buildCardsArray(List<Card> cards, XContentBuilder contentBuilder) {
        ofNullable(cards).ifPresent(_cards -> cards.stream().forEach(c -> {
            try {
                contentBuilder.startObject()
                        .field(CARD_VALUE, c.getValue())
                        .field(CARD_COLOR, c.getColor().name())
                        .field(CARD_DECK, c.getDeckId())
                        .field(CARD_NAME, c.getName())
                        .endObject();
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }));
    }

    private static String dateToString(LocalDateTime dateTime) {
        final DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        return dateTime.toString(dtf);
    }
}
