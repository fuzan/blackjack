package org.winning.blackjack.repository;

import org.winning.blackjack.people.Player;

public interface PlayerI {

    void savePlayerAction(Player player);

    void getPlayerGameRecord(String name, int gameId);
}
