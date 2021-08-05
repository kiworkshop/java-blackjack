package blackjack.domain.prize;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public class PlayerPrize {
    private final String playerName;
    private final Prize prize;
    private final Profit profit;

    public PlayerPrize(Player player, Dealer dealer) {
        this.playerName = player.getName();
        this.prize = Prize.of(player, dealer);
        this.profit = new Profit(player.getBetAmount(), this.prize);
    }

    public int getReceiveAmount() {
        return profit.getReceiveAmount();
    }

    public int getProfit() {
        return profit.getProfit();
    }

    public String getPlayerName() {
        return playerName;
    }
}
