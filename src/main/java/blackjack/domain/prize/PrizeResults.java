package blackjack.domain.prize;

import blackjack.domain.game.Table;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Status;

import java.util.ArrayList;
import java.util.List;

import static blackjack.domain.participant.Status.BLACKJACK;
import static blackjack.domain.participant.Status.BUST;

public class PrizeResults {
    public static final int BLACKJACK_RANK = 21;

    private final DealerPrize dealerPrize;
    private final PlayersPrize playersPrize;

    public PrizeResults(Dealer dealer, List<Player> players) {
        this.dealerPrize = new DealerPrize();
        this.playersPrize = new PlayersPrize(findPrizes(dealer, players));
    }

    public PrizeResults(Table table) {
        this(table.getDealer(), table.getPlayers());
    }

    private List<PlayerPrize> findPrizes(Dealer dealer, List<Player> players) {
        List<PlayerPrize> playersPrize = new ArrayList<>();
        for (Player player : players) {
            PlayerPrize playerPrize = findPrize(dealer, player);
            playersPrize.add(playerPrize);
        }
        return playersPrize;
    }

    private PlayerPrize findPrize(Dealer dealer, Player player) {
        final Status dealerStatus = Status.of(dealer);
        final Status playerStatus = Status.of(player);

        if (dealerStatus == BUST || playerStatus == BUST) {
            Prize prize = prizeOnBust(dealerStatus, playerStatus);
            return new PlayerPrize(player.getName(), prize);
        }

        if (dealerStatus == BLACKJACK || playerStatus == BLACKJACK) {
            Prize prize = prizeOnBlackjack(dealerStatus, playerStatus);
            return new PlayerPrize(player.getName(), prize);
        }

        Prize prize = prizeOnHold(dealer, player);
        return new PlayerPrize(player.getName(), prize);
    }

    private Prize prizeOnBust(Status dealer, Status player) {
        if (dealer == BUST && player == BUST) {
            dealerPrize.incrementLoseCount();
            return Prize.LOSE;
        }
        if (dealer == BUST) {
            dealerPrize.incrementLoseCount();
            return Prize.WIN;
        }
        dealerPrize.incrementWinCount();
        return Prize.LOSE;
    }

    private Prize prizeOnBlackjack(Status dealer, Status player) {
        if (dealer == BLACKJACK && player == BLACKJACK) {
            dealerPrize.incrementWinCount();
            return Prize.WIN;
        }
        if (dealer == BLACKJACK) {
            dealerPrize.incrementWinCount();
            return Prize.LOSE;
        }
        dealerPrize.incrementLoseCount();
        return Prize.WIN;
    }

    private Prize prizeOnHold(Dealer dealer, Player player) {
        int dealerGapAmount = Math.abs(BLACKJACK_RANK - dealer.sumRank());
        int playerGapAmount = Math.abs(BLACKJACK_RANK - player.sumRank());

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

    public DealerPrize getDealerPrize() {
        return dealerPrize;
    }

    public PlayersPrize getPlayersPrize() {
        return playersPrize;
    }
}
