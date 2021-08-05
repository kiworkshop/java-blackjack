package blackjack.domain.prize;

import blackjack.domain.game.Table;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayersPrize {
    private final List<PlayerPrize> playerPrizes;

    public PlayersPrize(Table table) {
        this.playerPrizes = Collections.unmodifiableList(findPlayersPrize(table));
    }

    private List<PlayerPrize> findPlayersPrize(Table table) {
        final Dealer dealer = table.getDealer();
        List<PlayerPrize> playersPrize = new ArrayList<>();
        for (Player player : table.getPlayers()) {
            PlayerPrize playerPrize = new PlayerPrize(player, dealer);
            playersPrize.add(playerPrize);
        }
        return playersPrize;
    }

    public int calculateTotalAmount() {
        return playerPrizes.stream()
                .mapToInt(PlayerPrize::getReceiveAmount)
                .sum();
    }

    public List<PlayerPrize> getPlayerPrizes() {
        return playerPrizes;
    }
}
