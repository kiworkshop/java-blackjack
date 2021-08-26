package blackjack.domain.prize;

import blackjack.domain.game.Hands;
import blackjack.domain.game.Table;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

import java.util.List;
import java.util.stream.Collectors;

public class PrizeResults {
    public static final int BLACKJACK_RANK = 21;

    private final DealerPrize dealerPrize;
    private final PlayersPrize playersPrize;

    public PrizeResults(Dealer dealer, List<Player> players) {
        this.dealerPrize = new DealerPrize();
        this.playersPrize = new PlayersPrize(comparePrize(dealer, players));
    }

    public PrizeResults(Table table) {
        this(table.dealer(), table.players());
    }

    private List<PlayerPrize> comparePrize(Dealer dealer, List<Player> players) {
        return players.stream()
                .map(player -> matchPrize(dealer, player))
                .collect(Collectors.toList());
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

        int dealerScore = getDifference(dealer.hands());
        int playerScore = getDifference(player.hands());
        Prize prize = generalPrize(dealerScore, playerScore);
        return new PlayerPrize(player.getName(), prize);
    }

    private int getDifference(Hands hands) {
        return BLACKJACK_RANK - hands.sumRank();
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

    private Prize generalPrize(int dealerScore, int playerScore) {
        if (dealerScore == playerScore) {
            dealerPrize.incrementTieCount();
            return Prize.TIE;
        }
        if (dealerScore < playerScore) {
            dealerPrize.incrementWinCount();
            return Prize.LOSE;
        }
        dealerPrize.incrementLoseCount();
        return Prize.WIN;
    }

    public DealerPrize dealerPrize() {
        return dealerPrize;
    }

    public PlayersPrize playersPrize() {
        return playersPrize;
    }
}
