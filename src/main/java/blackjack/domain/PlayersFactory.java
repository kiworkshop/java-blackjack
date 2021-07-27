package blackjack.domain;

import blackjack.utils.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PlayersFactory {
    public static List<Player> createPlayers(String input) {
        List<String> playerNames = StringUtils.splitByComma(input);

        List<Player> players = playerNames.stream()
                .map(Player::new)
                .collect(Collectors.toList());
        return Collections.unmodifiableList(players);
    }
}
