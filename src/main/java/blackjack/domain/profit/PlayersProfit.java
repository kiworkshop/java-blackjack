package blackjack.domain.profit;

import blackjack.domain.prize.PlayersPrize;
import blackjack.domain.table.BettingTable;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PlayersProfit {
    private final List<PlayerProfit> playerProfits;

    public PlayersProfit(BettingTable bettingTable, PlayersPrize prizes) {
        this.playerProfits = Collections.unmodifiableList(calculatePlayersProfit(bettingTable, prizes));
    }

    private List<PlayerProfit> calculatePlayersProfit(BettingTable bettingTable, PlayersPrize prizes) {
        return prizes.getPlayerNames().stream()
                .map(playerName -> new PlayerProfit(playerName,
                        bettingTable.getBetAmount(playerName),
                        prizes.getPrize(playerName)))
                .collect(Collectors.toList());
    }

    public int calculateTotalAmount() {
        return playerProfits.stream()
                .mapToInt(PlayerProfit::getReceiveAmount)
                .sum();
    }

    public List<PlayerProfit> getPlayerPrizes() {
        return playerProfits;
    }
}
