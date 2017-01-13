package org.winning.blackjack.deserialize;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class JsonDeseria implements ObjectdeserializeInter<String> {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private ObjectMapper mapper;

    public JsonDeseria(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Optional<String> convertObjectToJsonString(Object object) {
        return convertObjectToJson(object);
    }

    private Optional<String> convertObjectToJson(Object object) {
        try {
            return Optional.of(mapper.writeValueAsString(object));
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage(), e);
        }
        return Optional.empty();
    }
}