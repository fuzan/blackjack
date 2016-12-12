package org.winning.blackjack.gamblingactions;

import static org.winning.blackjack.entity.Result.BUSTED;
import static org.winning.blackjack.entity.Result.GO_TO_DEALER;
import static org.winning.blackjack.entity.Result.MIDDLE_GAME;

import org.winning.blackjack.CardValueUtil.CardSumHelper;
import org.winning.blackjack.entity.Card;
import org.winning.blackjack.entity.CardSum;
import org.winning.blackjack.entity.Result;
import org.winning.blackjack.people.Player;
import org.winning.blackjack.people.SplitPlayer;

public class PlayerAction extends PlayerDealerCommonAction{

    public PlayerAction(Player player) {
        super(player);
        player.setPlayerAction(this);
    }

    @Override
    public Result hit(Card card) {
        if (player.getCurrentSum().getSum() <= 21 ||
            player.getCurrentSum().getAlternativeSum() <= 21) {
            dealCardsToPlayerOrDealer(card);
        }
        if (player.getCurrentSum().getSum() > 21) {
            player.setResult(BUSTED);
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
            dealCardsToPlayerOrDealer(card);
            ((Player) player).setStake(((Player) player).getStake() - ((Player) player).getBetting());
            ((Player) player).setBetting(((Player) player).getBetting() * 2);
        }
        // go to stand by queue
        return GO_TO_DEALER;
    }

    @Override
    public Player[] split() {
        if (player instanceof Player) {
            if (((Player) player).isCanSplit()) {
                SplitPlayer splitPlayer =
                        new SplitPlayer(player.getName() + "_new_player_1.1", player.getName() + "_new_player_1.2",
                                        ((Player) player));
                ((Player) player).setStake(((Player) player).getStake() - ((Player) player).getBetting());
                return new Player[]{splitPlayer.getPlayer1(), splitPlayer.getPlayer2()};
            }
        }
        // go to ask player interaction
        return null;
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
}
