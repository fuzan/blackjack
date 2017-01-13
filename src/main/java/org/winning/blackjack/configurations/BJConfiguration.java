package org.winning.blackjack.configurations;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.hibernate.validator.constraints.NotEmpty;

import io.dropwizard.Configuration;

public class BJConfiguration extends Configuration {

    @NotEmpty
    private int deckNumber;

    @NotEmpty
    private int factor;

    @NotEmpty
    private int enumerator;

    @NotEmpty
    private int playerWaiting;

    private BJMongoDbConfiguration BJMongoDbConfiguration;

    @JsonProperty
    public BJMongoDbConfiguration getBJMongoDbConfiguration() {
        return BJMongoDbConfiguration;
    }

    @JsonProperty
    public void setBJMongoDbConfiguration(BJMongoDbConfiguration BJMongoDbConfiguration) {
        this.BJMongoDbConfiguration = BJMongoDbConfiguration;
    }

    @JsonProperty
    public int getPlayerWaiting() {
        return playerWaiting;
    }

    @JsonProperty
    public void setPlayerWaiting(int playerWaiting) {
        this.playerWaiting = playerWaiting;
    }

    @JsonProperty
    public int getDeckNumber() {
        return deckNumber;
    }

    @JsonProperty
    public void setDeckNumber(int deckNumber) {
        this.deckNumber = deckNumber;
    }

    @JsonProperty
    public int getFactor() {
        return factor;
    }

    @JsonProperty
    public void setFactor(int factor) {
        this.factor = factor;
    }

    @JsonProperty
    public int getEnumerator() {
        return enumerator;
    }

    @JsonProperty
    public void setEnumerator(int enumerator) {
        this.enumerator = enumerator;
    }

}

