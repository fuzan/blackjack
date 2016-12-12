package org.winning.blackjack.gamblingactions;

import static org.winning.blackjack.entity.Result.BUSTED;
import static org.winning.blackjack.entity.Result.GO_TO_DEALER;
import static org.winning.blackjack.entity.Result.LOST;
import static org.winning.blackjack.entity.Result.WIN;

import org.winning.blackjack.CardValueUtil.BJLogger;
import org.winning.blackjack.entity.Action;
import org.winning.blackjack.entity.Card;
import org.winning.blackjack.entity.Result;
import org.winning.blackjack.people.BaseUser;
import org.winning.blackjack.people.Dealer;
import org.winning.blackjack.people.Player;
import org.winning.blackjack.people.SplitPlayer;

import java.util.LinkedList;
import java.util.List;

public class BlackJackGame {

    private Dealer dealer;
    private List<Player> players;
    private List<Player> standbyPlayers;
    private List<Card> cards;

    private int deckNumber;

    private BJLogger logger;

    public BlackJackGame(Dealer dealer, List<Player> players, int deckNumber,
                         BJLogger logger) {
        this.dealer = dealer;
        this.players = players;
        this.deckNumber = deckNumber;
        this.logger = logger;
        this.standbyPlayers = new LinkedList<>();
    }

    public List<Player> getStandbyPlayers() {
        return standbyPlayers;
    }

    public void setStandbyPlayers(List<Player> standbyPlayers) {
        this.standbyPlayers = standbyPlayers;
    }

    public int getDeckNumber() {
        return deckNumber;
    }

    public void setDeckNumber(int deckNumber) {
        this.deckNumber = deckNumber;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public void setDealer(Dealer dealer) {
        this.dealer = dealer;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }


    public void ifPlayerDontWantToPlay(Player player, boolean inGame) {
        player.setInGame(inGame);
    }

    public void dealCardsToDealer() {
        dealer.getCommonActions().dealTwoCardsToPlayer(getNextCard(true), getNextCard(false));
        logger.logPlayerOrDealerStatus(dealer);
    }

    //deal two cards to player and dealer
    public void begin(Player player) {
        player.getCommonActions().dealTwoCardsToPlayer(getNextCard(true), getNextCard(true));
        logger.logPlayerOrDealerStatus(player);
        judgeBJOrSplitable(player);
        //go to ask player action
    }

    public void askPlayerToBet(Player player, int betting) {
        player.setBetting(betting);
        player.setStake(player.getStake() - betting);
    }

    public void endGame() {
        dealerTurn();
    }

    public void endGamePlayer(Player player) {
        judge(player);
    }

    public void clearStandBy() {
        standbyPlayers.clear();
    }

    public void askPlayer(Action action, Player player) {
        final Card random = getNextCard(true);
        switch (action) {
            case STAND:
                player.getPlayerAction().stand();
                standbyPlayers.add(player);
                break;
            case HIT:
                Result hit_result = player.getPlayerAction().hit(random);
                if (GO_TO_DEALER.equals(hit_result)) {
                    standbyPlayers.add(player);
                }
                logger.logPlayerOrDealerStatus(player);
                break;
            case DOUBLE:
                player.getPlayerAction().double_betting(random);
                logger.logPlayerOrDealerStatus(player);
                standbyPlayers.add(player);
                break;
            case SPLIT:
                if (player.isCanSplit()) {
                    SplitPlayer splitPlayer =
                            new SplitPlayer(player.getName() + "_new_player_1.1", player.getName() + "_new_player_1.2",
                                            player);
                    player.setStake(player.getStake() - player.getBetting());
                    player.setTwoSplitedPlayer(new Player[]{splitPlayer.getPlayer1(), splitPlayer.getPlayer2()});
                }
                break;
            default:
                break;

        }
    }

    private BaseUser dealerTurn() {
        // if dealer is soft

        //hit to soft17
        while (dealer.getCurrentSum().getSum() < 17) {
            if (dealer.getCurrentSum().getAlternativeSum() > 17
                && dealer.getCurrentSum().getAlternativeSum() < 21) {
                return dealer;
            }
            dealer.getCommonActions().dealCardsToPlayerOrDealer(getNextCard(true));
        }

        if (dealer.getCurrentSum().getSum() > 21) {
            dealer.setResult(BUSTED);
        }
        // do nothing if 17 to 21
        // dealer turn ends
        logger.logPlayerOrDealerStatus(dealer);
        return dealer;
    }

    private void judgeBJOrSplitable(Player player) {
        if (player.isBlackJack()) {
            System.out.println(player.getName() + " you are lucky !");
            player.getPlayerAction().stand();
        }
        if (player.isCanSplit()) {
            splitPrompt(player);
        }
    }

    private void judge(Player player) {
        final int playerSum = getPlayerBestSum(player);

        if (!dealer.getSecondCard().isShow()) {
            dealer.getSecondCard().setShow(true);
        }

        logger.logPlayerOrDealerStatus(dealer);
        logger.logPlayerOrDealerStatus(dealer);

        final int dealerSum = getPlayerBestSum(dealer);
        if (playerSum <= 21) {
            if (playerSum > dealerSum || BUSTED.equals(dealer.getResult())) {
                player.setResult(WIN);
            } else if (playerSum == dealerSum) {
                player.setResult(Result.DRAW);
            } else {
                player.setResult(LOST);
            }
        } else {
            if (BUSTED.equals(dealer.getResult())) {
                player.setResult(Result.DRAW);
            } else {
                player.setResult(LOST);
            }
        }
        judgePlayerBetting(player);
        player.reset();
    }

    private Card getNextCard(boolean isFaceUp) {

        if (cards == null || cards.size() == 0) {
            shuffle();
        }

        int random = CardOperator.generateRandomCard(cards.size());
        final Card randomCard = cards.get(random - 1);
        randomCard.setShow(isFaceUp);
        cards.remove(randomCard);
        return randomCard;
    }

    private void shuffle() {
        this.cards = CardOperator.combineAllCards(deckNumber);
    }

    private int getPlayerBestSum(BaseUser user) {
        final int sum = user.getCurrentSum().getSum();
        final int alterSum = user.getCurrentSum().getAlternativeSum();
        if (alterSum <= 21) {
            return Math.max(sum, alterSum);
        } else {
            return sum;
        }
    }

    private void judgePlayerBetting(Player player) {
        switch (player.getResult()) {
            case WIN:
                int factor = 2;
                if (player.isBlackJack()) {
                    factor = 3;
                }
                if (player.isSplitted()) {
                    player.getParentPlayer()
                            .setStake(player.getParentPlayer().getStake() + player.getBetting() * factor);
                } else {
                    player.setStake(player.getStake() + player.getBetting() * factor);
                }
                break;
            case DRAW:
                player.setStake(player.getStake() + player.getBetting());
                break;
            case BUSTED:
            case LOST:
                break;
        }
        if (player.getParentPlayer() != null) {
            logger.logPlayerResultAndStake(player.getParentPlayer());
        } else {
            logger.logPlayerResultAndStake(player);
        }
        player.setResult(Result.NEW_GAME);
    }

    public void splitPrompt(Player player) {
        if (player.isCanSplit()) {
            logger.pleaseConsiderSplit(player);
        }
    }

}
