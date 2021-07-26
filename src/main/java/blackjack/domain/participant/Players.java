package blackjack.domain.participant;

import java.util.List;
import java.util.stream.Collectors;

public class Players {
    private final List<Player> players;

    public Players(List<String> userInput) {
        this.players = userInput.stream()
                .map(Player::new)
                .collect(Collectors.toList());
    }

    public int size() {
        return players.size();
    }
}
