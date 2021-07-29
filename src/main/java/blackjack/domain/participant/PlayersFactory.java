package blackjack.domain.participant;

import blackjack.utils.StringtUtil;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PlayersFactory {

    private static final String CHECK_DUPLICATED_PLAYER_NAME = "플레이어가 중복된 이름을 가지고 있는지 확인해주세요.";

    public static List<Player> createPlayers(String input) {
        List<String> playerNames = StringtUtil.splitByComma(input);
        checkDuplication(playerNames);

        List<Player> players = playerNames.stream()
                .map(Player::new)
                .collect(Collectors.toList());
        return Collections.unmodifiableList(players);
    }

    private static void checkDuplication(List<String> playerNames) {
        int distinctCount = (int) playerNames.stream()
                .distinct()
                .count();

        if (playerNames.size() != distinctCount) {
            throw new IllegalArgumentException(CHECK_DUPLICATED_PLAYER_NAME);
        }

    }
}
