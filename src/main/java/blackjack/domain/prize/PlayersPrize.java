package blackjack.domain.prize;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayersPrize {
    private final List<PlayerPrize> prizes;

    public PlayersPrize(List<PlayerPrize> playerPrizes) {
        this.prizes = Collections.unmodifiableList(new ArrayList<>(playerPrizes));
    }

    public List<PlayerPrize> values() {
        return prizes;
    }
}
