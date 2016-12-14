package org.winning.blackjack.gamblingactions;

import static org.winning.blackjack.entity.Action.HIT;
import static org.winning.blackjack.entity.Action.STAND;

import org.winning.blackjack.CardValueUtil.BJLogger;
import org.winning.blackjack.entity.Action;
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

    private BJLogger logger = new BJLogger();

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
            logger.pleaseBet(player);
            blackJackGame.askPlayerToBet(player, input.askPlayerBetting());
        }

        blackJackGame.dealCardsToDealer();

        blackJackGame.getPlayers().stream().filter(p -> p.isInGame()).forEach(blackJackGame::begin);

        for (Player player : blackJackGame.getPlayers()) {
            if (!player.isInGame()) {
                continue;
            }
            if (player.isBlackJack()) {
                noActionForBJPlayer(blackJackGame, player);
                continue;
            }
            while (true) {
                if (hitUntilStandOrBusted(blackJackGame, player)) {
                    if (player.isSplitted()) {
                        splitPlayerTilStandOrBusted(blackJackGame, player);
                    }
                    break;
                }
            }
        }
        blackJackGame.endGame();
        blackJackGame.getStandbyPlayers().stream().forEach(blackJackGame::endGamePlayer);
        blackJackGame.clearStandBy();
    }

    private void splitPlayerTilStandOrBusted(BlackJackGame blackJackGame, Player player) {
        for (Player splitPlayer : player.getTwoSplitedPlayer()) {
            while (true) {
                if (hitUntilStandOrBusted(blackJackGame, splitPlayer)) {
                    break;
                }
            }
        }
    }

    private void noActionForBJPlayer(BlackJackGame blackJackGame, Player player) {
        blackJackGame.askPlayer(STAND, player);
    }

    private boolean hitUntilStandOrBusted(BlackJackGame blackJackGame, Player player) {
        System.out.println(player.getName() + ": please");
        Action action2 = askPlayerAction();
        blackJackGame.askPlayer(action2, player);
        if (!action2.equals(HIT) || player.getCurrentSum().getSum() > 21) {
            return true;
        }
        return false;
    }

    private boolean askPlayerWantToBeginPlay() {
        String yesOrNo = input.askPlayerWantToBegin();
        return "yes".equalsIgnoreCase(yesOrNo) ? true : false;
    }

    private Action askPlayerAction() {
        String actionStr = input.askPlayerAction();
        try {
            return Action.valueOf(actionStr.toUpperCase());
        } catch (Exception ex) {
            logger.logException("user action input is invalid ! ", ex);
            return STAND;
        }
    }
}
