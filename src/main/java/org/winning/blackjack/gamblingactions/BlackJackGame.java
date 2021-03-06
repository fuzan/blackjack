package org.winning.blackjack.gamblingactions;

import static org.winning.blackjack.entity.Result.GO_TO_DEALER;
import static org.winning.blackjack.entity.Result.LOST;
import static org.winning.blackjack.entity.Result.WIN;

import org.winning.blackjack.CardValueUtil.BJLogger;
import org.winning.blackjack.CardValueUtil.CardSumHelper;
import org.winning.blackjack.entity.Action;
import org.winning.blackjack.entity.Card;
import org.winning.blackjack.entity.Chips;
import org.winning.blackjack.entity.Result;
import org.winning.blackjack.people.BaseUser;
import org.winning.blackjack.people.Dealer;
import org.winning.blackjack.people.Player;

import java.util.ArrayList;
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
        dealer.getBindingAction().dealTwoCardsToPlayer(getNextCard(true), getNextCard(false));
        logger.logPlayerOrDealerStatus(dealer);
    }

    //deal two cards to player and dealer
    public void begin(Player player) {
        player.getBindingAction().dealTwoCardsToPlayer(getNextCard(true), getNextCard(true));
        logger.logPlayerOrDealerStatus(player);
        judgeBJOrSplitable(player);
    }

    public void askPlayerToBet(Player player, int betting) {
        final List<Chips> bettings = new ArrayList();
        bettings.add(Chips.getChip(betting));
        player.setBetting(bettings);
        player.setStake(player.getStake() - CardSumHelper.calculateBetting(bettings));
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
                player.getBindingAction().stand();
                standbyPlayers.add(player);
                break;
            case HIT:
                Result hit_result = player.getBindingAction().hit(random);
                if (GO_TO_DEALER.equals(hit_result)) {
                    standbyPlayers.add(player);
                }
                logger.logPlayerOrDealerStatus(player);
                break;
            case DOUBLE:
                player.getBindingAction().double_betting(random);
                standbyPlayers.add(player);
                logger.logPlayerOrDealerStatus(player);
                break;
            case SPLIT:
                if (player.isCanSplit()) {
                    player.getBindingAction().split();
                }
                break;
            case SURRANDER:
                standbyPlayers.add(player);
                player.getBindingAction().surrander();
            default:
                break;

        }
    }

    private BaseUser dealerTurn() {
        //hit to soft17
        while (dealer.getCurrentSum().getSum() < 17) {
            if (dealer.getCurrentSum().getAlternativeSum() > 17
                && dealer.getCurrentSum().getAlternativeSum() < 21) {
                return dealer;
            }
            dealer.getBindingAction().dealCardsToPlayerOrDealer(getNextCard(true));
        }
        // do nothing if 17 to 21
        // dealer turn ends
        return dealer;
    }

    private void judgeBJOrSplitable(Player player) {
        bjPrompt(player);
        splitPrompt(player);
    }

    private void judge(Player player) {
        final int playerSum = getPlayerBestSum(player);

        if (!dealer.getSecondCard().isShow()) {
            dealer.getSecondCard().setShow(true);
            // only log dealer once
            logger.logPlayerOrDealerStatus(dealer);
        }

        final int dealerSum = getPlayerBestSum(dealer);
        if (playerSum <= 21) {
            if (playerSum > dealerSum || dealerSum > 21) {
                player.setResult(WIN);
            } else if (playerSum == dealerSum) {
                player.setResult(Result.DRAW);
            } else {
                player.setResult(LOST);
            }
        } else {
            if (dealerSum > 21) {
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
        int betting = CardSumHelper.calculateBetting(player.getBetting());
        switch (player.getResult()) {
            case WIN:
                int factor = player.isBlackJack() ? 3 : 2;
                if (player.isSplitted()) {
                    player.getParentPlayer().setStake(player.getParentPlayer().getStake() + betting * factor);
                } else {
                    player.setStake(player.getStake() + betting * factor);
                }
                break;
            case DRAW:
                player.setStake(player.getStake() + betting);
                break;
            case LOST:
                break;
        }
        if (player.isSplitted()) {
            logger.logPlayerResultAndStake(player.getParentPlayer());
        } else {
            logger.logPlayerResultAndStake(player);
        }
    }

    private void splitPrompt(Player player) {
        if (player.isCanSplit()) {
            logger.pleaseConsiderSplit(player);
        }
    }

    private void bjPrompt(Player player) {
        if (player.isBlackJack()) {
            logger.luckyDog(player);
        }
    }
}
