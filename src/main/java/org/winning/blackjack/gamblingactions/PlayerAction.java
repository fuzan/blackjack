package org.winning.blackjack.gamblingactions;

import static org.winning.blackjack.entity.Result.GO_TO_DEALER;
import static org.winning.blackjack.entity.Result.MIDDLE_GAME;

import org.winning.blackjack.CardValueUtil.CardSumHelper;
import org.winning.blackjack.entity.Card;
import org.winning.blackjack.entity.CardSum;
import org.winning.blackjack.entity.Result;
import org.winning.blackjack.people.Player;
import org.winning.blackjack.people.SplitPlayer;

public class PlayerAction extends PlayerDealerCommonAction {

    public static final String NEW_PLAYER_1_1 = "_new_player_1.1";
    public static final String NEW_PLAYER_1_2 = "_new_player_1.2";

    @Override
    public Result hit(Card card) {
        if (player.getCurrentSum().getSum() < 21 ||
            player.getCurrentSum().getAlternativeSum() < 21) {
            dealCardsToPlayerOrDealer(card);
        }
        if (player.getCurrentSum().getSum() >= 21) {
            // can go to stand by queue
            return GO_TO_DEALER;
        }
        // can go back to ask player interaction
        return MIDDLE_GAME;
    }

    @Override
    public Result stand() {
        // can go to stand by queue
        return GO_TO_DEALER;
    }

    @Override
    public Result double_betting(Card card) {
        if (player instanceof Player) {

            int bettings = CardSumHelper.calculateBetting(((Player) player).getBetting());

            dealCardsToPlayerOrDealer(card);
            ((Player) player).setStake(((Player) player).getStake() - bettings);
            ((Player) player).setBetting(CardSumHelper.doubleChips(((Player) player).getBetting()));
        }
        // go to stand by queue
        return GO_TO_DEALER;
    }

    @Override
    public Result split() {
        if (player instanceof Player) {
            if (((Player) player).isCanSplit()) {
                SplitPlayer splitPlayer =
                        new SplitPlayer(player.getName() + NEW_PLAYER_1_1, player.getName() + NEW_PLAYER_1_2,
                                        ((Player) player));
                ((Player) player)
                        .setStake(((Player) player).getStake() - CardSumHelper
                                .calculateBetting(((Player) player).getBetting()));
                ((Player) player).setSplitedPlayer(splitPlayer);
                return MIDDLE_GAME;
            }
        }
        // go to ask player interaction
        return GO_TO_DEALER;
    }

    @Override
    CardSum dealCardsToPlayerOrDealer(Card card) {
        return super.dealCardsToPlayerOrDealer(card);
    }

    @Override
    protected void dealTwoCardsToPlayer(Card firstCard, Card secondCard) {
        player.getAllCards().add(firstCard);
        player.getAllCards().add(secondCard);
        player.setCurrentSum(CardSumHelper.getSum(firstCard, secondCard));
    }

    @Override
    public Result surrander() {
        int bettings = CardSumHelper.calculateBetting(((Player) player).getBetting());
        ((Player) player).setStake(((Player) player).getStake() + bettings / 2);
        return GO_TO_DEALER;
    }
}
