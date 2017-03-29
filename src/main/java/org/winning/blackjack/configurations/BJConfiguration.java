package org.winning.blackjack.configurations;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import io.dropwizard.Configuration;

public class BJConfiguration extends Configuration {

    @Min(1)
    @Max(16)
    private int deckNumber;

    @Min(3)
    @Max(9)
    private int factor;

    @Min(2)
    @Max(16)
    private int enumerator;

    @Min(20)
    @Max(100000)
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

