package blackjack.domain.prize;

import blackjack.domain.game.Table;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

import java.util.ArrayList;
import java.util.List;

public class ParticipantsPrize {
    private final DealerPrize dealerPrize;
    private final PlayersPrize playersPrize;

    public ParticipantsPrize(Table table) {
        this.playersPrize = new PlayersPrize(table);
        this.dealerPrize = new DealerPrize(table.calculateTotalBetAmount(), playersPrize.calculateTotalAmount());
    }

    public int getDealerProfit() {
        return dealerPrize.getProfit();
    }

    public List<PlayerPrize> getPlayersPrize() {
        return playersPrize.getPlayerPrizes();
    }
}
