package blackjack.dto;

import static blackjack.exception.ExceptionMessage.INVALID_BET_AMOUNT_MESSAGE;

public class PlayerInput {
    public static final int MINIMUM_BET_AMOUNT = 1;

    private final String playerName;
    private final int betAmount;

    public PlayerInput(String playerName, int betAmount) {
        validateBetAmount(betAmount);

        this.playerName = playerName;
        this.betAmount = betAmount;
    }

    private void validateBetAmount(int betAmount) {
        if (betAmount < MINIMUM_BET_AMOUNT) {
            throw new IllegalArgumentException(INVALID_BET_AMOUNT_MESSAGE);
        }
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getBetAmount() {
        return betAmount;
    }
}
