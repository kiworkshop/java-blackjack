package blackjack.domain.prize;

import blackjack.domain.game.Table;
import blackjack.domain.participant.Dealer;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PlayersPrize {
    private final List<PlayerPrize> playerPrizes;

    public PlayersPrize(Table table) {
        this.playerPrizes = Collections.unmodifiableList(findPlayersPrize(table));
    }

    private List<PlayerPrize> findPlayersPrize(Table table) {
        Dealer dealer = table.getDealer();
        return table.getPlayers().stream()
                .map(player -> new PlayerPrize(player, dealer))
                .collect(Collectors.toList());
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
