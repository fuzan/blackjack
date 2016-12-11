package org.winning.blackjack.gamblingactions;

import org.winning.blackjack.entity.Card;
import org.winning.blackjack.entity.Result;
import org.winning.blackjack.people.BaseUser;
import org.winning.blackjack.people.Player;

public interface BlackJackAction {

    Result hit(BaseUser player, Card card);

    Result stand(BaseUser player);

    Result double_betting(BaseUser player, Card card);

    Player[] split(BaseUser player);
}
