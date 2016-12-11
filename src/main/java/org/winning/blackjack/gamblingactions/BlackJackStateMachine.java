package org.winning.blackjack.gamblingactions;

import static org.winning.blackjack.entity.Action.DOUBLE;
import static org.winning.blackjack.entity.Action.SPLIT;
import static org.winning.blackjack.entity.Action.STAND;
import static org.winning.blackjack.entity.Result.BUSTED;

import org.apache.commons.collections4.keyvalue.MultiKey;
import org.apache.commons.collections4.map.MultiKeyMap;
import org.winning.blackjack.entity.Action;
import org.winning.blackjack.entity.GameStatus;
import org.winning.blackjack.input.PlayerInteractionInput;
import org.winning.blackjack.people.Player;

/**
 * a state machine determine which state goes to
 * eg :
 *
 * ask player to bet : player bets
 *
 * game begin : dealer deals card to himself, and deal cards to player
 *
 * game judge : ask player want to buy insurance or not
 *
 * game judge : dealer is BJ or not ? player is BJ or not ?
 *
 * game judge : if deal is and player is not, game end, calculate stake
 *
 * if player is and dealer is not, game end
 *
 * if both is, game end
 *
 * if both not continue
 *
 * game continue : ask player action ?
 *
 * base on action go to deal or go to judge
 *
 * game judge : judge player and dealer card sum, calculate stake
 *
 * game ends : ask player want to start over or not
 */
public class BlackJackStateMachine {

    private PlayerInteractionInput input;

    // recording game status
    // id : player name and dealer name, it should be unique, game status
    private MultiKeyMap<MultiKey, GameStatus> status = new MultiKeyMap<>();

    public BlackJackStateMachine(PlayerInteractionInput input) {
        this.input = input;
    }

    public void playBlackJack(BlackJackGame blackJackGame) {

        for (Player p : blackJackGame.getPlayers()) {
            boolean beginOrNot = askPlayerWantToBeginPlay();
            blackJackGame.ifPlayerDontWantToPlay(p, beginOrNot);
        }

        for (Player player : blackJackGame.getPlayers()) {
            if (!player.isInGame()) {
                continue;
            }
            System.out.println(player.getName() + "please bet : ");
            blackJackGame.askPlayerToBet(player, input.askPlayerBetting());
        }

        blackJackGame.dealCardsToDealer();

        blackJackGame.getPlayers().stream().filter(p -> p.isInGame()).forEach(blackJackGame::begin);

        for (Player player : blackJackGame.getPlayers()) {
            if (!player.isInGame()) {
                continue;
            }
            System.out.println(player.getName() + ", please chose your action : ");

            while (true) {
                Action action = askPlayerAction();
                blackJackGame.askPlayer(action, player);
                if (action.equals(DOUBLE) || action.equals(STAND) || BUSTED.equals(player.getResult())) {
                    break;
                }
                if( SPLIT.equals(action) ){
                    Player player1 = player.getTwoSplitedPlayer()[0];
                    Player player2 = player.getTwoSplitedPlayer()[1];
                }
            }
        }

        blackJackGame.endGame();
        blackJackGame.getStandbyPlayers().stream().filter(p -> p.isInGame()).forEach(blackJackGame::endGamePlayer);
        blackJackGame.clearStandBy();
    }

    private boolean askPlayerWantToBeginPlay() {
        String yesOrNo = input.askPlayerWantToBegin();
        return "yes".equalsIgnoreCase(yesOrNo) ? true : false;
    }

    private Action askPlayerAction() {
        String actionStr = input.askPlayerAction();
        return Action.valueOf(actionStr.toUpperCase());
    }
}
