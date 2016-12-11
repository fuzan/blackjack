package org.winning.blackjack.CardValueUtil;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.winning.blackjack.entity.Card;
import org.winning.blackjack.people.BaseUser;
import org.winning.blackjack.people.Dealer;
import org.winning.blackjack.people.Player;

import java.util.List;

public class BJLogger {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public void logPlayerOrDealerFirstTwoCard(BaseUser player) {
        logger.info(" player {}'s first cars is {} and second card is {}", player.getName(),
                    getCardDetails(player.getFirstCard()), getCardDetails(player.getSecondCard()));
    }

    public void logPlayerNextCard(Player player) {
        final StringBuilder builder = new StringBuilder();
        List<Card> allCards = player.getOtherCards();
        if (allCards != null && allCards.size() > 0) {
            allCards.stream().forEach(card -> builder.append("{" + getCardDetails(card) + "}, "));
        }
        logger.info(" player {}'s cards are : {}", player.getName(), builder.toString());
    }


    public void logPlayerBehavior(BaseUser player) {
        StringBuilder builder = new StringBuilder();
        player.getOtherCards().stream().forEach(c -> builder.append(c.getName() + ", "));
        logger.info(" Player {} got cards : " + player.getName());
        logger.info(builder.toString());
    }

    public void logDealerBehavior(Dealer dealer) {
        final String secondCardInfo = dealer.getSecondCard().isShow() ? getCardDetails(dealer.getSecondCard()) : "";
        logger.info(" dealer {}'s first cars is {} and second card is {}", dealer.getName(),
                    getCardDetails(dealer.getFirstCard()), secondCardInfo);
    }

    public void pleaseConsiderSplit(BaseUser player) {
        logger.info(player.getName() + " Please consider split !!!! ");
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
