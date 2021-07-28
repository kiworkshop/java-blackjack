package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class GameService {
    static final int BLACKJACK = 21;
    static final int ACE_BONUS_SCORE = 10;

    private final List<Player> players = new ArrayList<>();

    public GameService(List<String> playerNames) {
        init(playerNames);
    }

    private void init(List<String> playerNames) {
        playerNames.forEach(playerName -> {
            players.add(new Player(playerName));
        });
    }

    public List<Player> getPlayers() {
        return players;
    }
}
