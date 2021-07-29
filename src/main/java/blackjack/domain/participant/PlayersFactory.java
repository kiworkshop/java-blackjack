package blackjack.domain.participant;

import blackjack.utils.SplitUtil;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PlayersFactory {
    public static List<Player> createPlayers(String input) {
        List<String> playerNames = SplitUtil.splitByComma(input);

        List<Player> players = playerNames.stream()
                .map(Player::new)
                .collect(Collectors.toList());
        return Collections.unmodifiableList(players);
    }
}
