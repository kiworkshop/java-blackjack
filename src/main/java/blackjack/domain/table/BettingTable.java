package blackjack.domain.table;

import blackjack.dto.PlayerInput;
import blackjack.exception.NoSuchPlayerException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class BettingTable {
    private final Map<String, Integer> bettingTable = new HashMap<>();

    public BettingTable(List<PlayerInput> playerInputs) {
        playerInputs.forEach(
                playerInput -> bettingTable.put(playerInput.getPlayerName(), playerInput.getBetAmount())
        );
    }

    public int calculateTotalBetAmount() {
        return bettingTable.values().stream()
                .mapToInt(Integer::valueOf)
                .sum();
    }

    public int getBetAmount(String playerName) {
        Integer betAmount = bettingTable.get(playerName);

        if (Objects.isNull(betAmount)) {
            throw new NoSuchPlayerException(playerName);
        }

        return betAmount;
    }
}
