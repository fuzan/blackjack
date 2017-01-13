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
            logger.info("{} sum is {} ", player.getName(), player.getCurrentSum().getAlternativeSum() < 21 ? Math
                    .max(player.getCurrentSum().getSum(), player.getCurrentSum().getAlternativeSum()) : player
                                                                   .getCurrentSum().getSum());
        }
        logger.info("---------------------------------------------------------------------------");
    }

    public void logPlayerResultAndStake(Player player) {
        logger.info("{} is {} and remaining stake is {}", player.getName(), player.getResult(), player.getStake());
    }

    public void pleaseConsiderSplit(BaseUser player) {
        logger.info("{} Please consider split !!!! " + player.getName());
    }

    public void luckyDog(BaseUser player) {
        logger.info("{} BJ !, you are lucky ! : ", player.getName());
    }

    public void pleaseBet(BaseUser player) {
        logger.info("{}, please bet : ", player.getName());
    }

    public void logException(String message, Exception ex) {
        logger.error(message + ", " + ex.getMessage(), ex);
    }

    private String getCardDetails(Card card) {

        return String.format(  "\ncard value is %s, \n"
                             + "card name is %s, \n"
                             + "card color is %s, \n"
                             + "card deck is %s\n", card.getValue(), card.getName(), card.getColor().name(),
                             card.getDeckId());
    }
}