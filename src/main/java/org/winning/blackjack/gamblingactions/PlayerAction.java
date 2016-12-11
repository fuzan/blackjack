package org.winning.blackjack.gamblingactions;

import org.winning.blackjack.entity.Result;
import org.winning.blackjack.people.Dealer;
import org.winning.blackjack.people.Player;

public class PlayerAction implements BlackJackAction{

    @Override
    public Result hit(Player player, Dealer dealer) {
        return null;
    }

    @Override
    public Result stand(Player player, Dealer dealer) {
        return null;
    }

    @Override
    public Result double_betting(Player player, Dealer dealer) {
        return null;
    }

    @Override
    public Result split(Player player) {
        return null;
    }
}
