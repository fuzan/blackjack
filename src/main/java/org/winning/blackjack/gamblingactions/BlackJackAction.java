package org.winning.blackjack.gamblingactions;

import org.winning.blackjack.entity.Card;
import org.winning.blackjack.entity.Result;

public interface BlackJackAction {

    Result hit(Card card);

    Result stand();

    Result double_betting(Card card);

    Result split();

    Result surrander();
}
