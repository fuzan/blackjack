package org.winning.blackjack.CardValueUtil;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.winning.blackjack.entity.Card;
import org.winning.blackjack.people.BaseUser;
import org.winning.blackjack.people.Player;

import java.util.Objects;

public class BJLogger {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public void logPlayerOrDealerStatus(BaseUser player) {
        logger.info("---------------------------------------------------------------------------");
        player.getAllCards().stream().filter(Objects::nonNull).filter(c -> c.isShow())
                .forEach(c -> logger.info(getCardDetails(c)));

        if (player.getAllCards().size() > 1 && !player.getAllCards().get(1).isShow()) {
            logger.info("Dealer sum is unknown now!");
        } else {
            logger.info("{} sum is {} ", player.getName(), player.getCurrentSum().getSum());
        }

        logger.info("---------------------------------------------------------------------------");
    }

    public void logPlayerResultAndStake(Player player) {
        logger.info("{} is {} and remaining stake is {}", player.getName(), player.getResult(), player.getStake());
    }

    public void pleaseConsiderSplit(BaseUser player) {
        logger.info("{} Please consider split !!!! " + player.getName());
    }

    public void pleaseBet(BaseUser player){
        logger.info("{}, please bet : ", player.getName());
    }
    public void logException(String message, Exception ex) {
        logger.error(message + ", " + ex.getMessage(), ex);
    }

    private String getCardDetails(Card card) {
        StringBuilder builder = new StringBuilder();
        builder.append(" card value :" + card.getValue());
        builder.append(" card name :" + card.getName());
        builder.append(" card color :" + card.getColor().name());
        builder.append(" card deck : " + card.getDeckId());
        return builder.toString();
    }
}
