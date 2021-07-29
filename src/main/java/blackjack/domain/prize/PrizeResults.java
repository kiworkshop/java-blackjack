package blackjack.domain.prize;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;

import java.util.ArrayList;
import java.util.List;

public class PrizeResults {
    public static final int BLACKJACK_RANK = 21;

    private final DealerPrize dealerPrize;
    private final PlayersPrize playersPrize;

    public PrizeResults(Dealer dealer, Players players) {
        this.dealerPrize = new DealerPrize();
        this.playersPrize = new PlayersPrize(comparePrize(dealer, players));
    }

    private List<PlayerPrize> comparePrize(Dealer dealer, Players players) {
        List<PlayerPrize> playersPrize = new ArrayList<>();
        for (Player player : players.getPlayers()) {
            PlayerPrize playerPrize = matchPrize(dealer, player);
            playersPrize.add(playerPrize);
        }
        return playersPrize;
    }

    private PlayerPrize matchPrize(Dealer dealer, Player player) {
        if (dealer.bust() || player.bust()) {
            Prize prize = bustPrize(dealer.bust(), player.bust());
            return new PlayerPrize(player.getName(), prize);
        }

        if (dealer.blackjack() || player.blackjack()) {
            Prize prize = blackjackPrize(dealer.blackjack(), player.blackjack());
            return new PlayerPrize(player.getName(), prize);
        }

        int dealerGapAmount = Math.abs(BLACKJACK_RANK - dealer.sumRank());
        int playerGapAmount = Math.abs(BLACKJACK_RANK - player.sumRank());
        Prize prize = generalPrize(dealerGapAmount, playerGapAmount);

        return new PlayerPrize(player.getName(), prize);
    }

    private Prize generalPrize(int dealerGapAmount, int playerGapAmount) {
        if (dealerGapAmount == playerGapAmount) {
            dealerPrize.incrementTieCount();
            return Prize.TIE;
        }
        if (dealerGapAmount < playerGapAmount) {
            dealerPrize.incrementWinCount();
            return Prize.LOSE;
        }
        dealerPrize.incrementLoseCount();
        return Prize.WIN;
    }

    private Prize blackjackPrize(boolean dealerBlackjack, boolean playerBlackjack) {
        if (dealerBlackjack && playerBlackjack) {
            dealerPrize.incrementWinCount();
            return Prize.WIN;
        }
        if (dealerBlackjack) {
            dealerPrize.incrementWinCount();
            return Prize.LOSE;
        }
        dealerPrize.incrementLoseCount();
        return Prize.WIN;
    }

    private Prize bustPrize(boolean dealerBust, boolean playerBust) {
        if (dealerBust && playerBust) {
            dealerPrize.incrementLoseCount();
            return Prize.LOSE;
        }
        if (dealerBust) {
            dealerPrize.incrementLoseCount();
            return Prize.WIN;
        }
        dealerPrize.incrementWinCount();
        return Prize.LOSE;
    }

    public DealerPrize getDealerPrize() {
        return dealerPrize;
    }

    public PlayersPrize getPlayersPrize() {
        return playersPrize;
    }
}
