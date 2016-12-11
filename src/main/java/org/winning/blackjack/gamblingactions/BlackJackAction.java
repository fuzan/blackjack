package org.winning.blackjack.gamblingactions;

import org.winning.blackjack.entity.Result;
import org.winning.blackjack.people.Dealer;
import org.winning.blackjack.people.Player;

public interface BlackJackAction {

    Result hit(Player player, Dealer dealer);

    Result stand(Player player, Dealer dealer);

    Result double_betting(Player player, Dealer dealer);

    Result split(Player player);
}
