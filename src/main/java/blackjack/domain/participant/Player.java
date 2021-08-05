package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.dto.PlayerInput;

import java.util.List;

import static blackjack.domain.game.Table.INITIAL_DEAL_COUNT;

public class Player extends Participant {
    private final String name;
    private final int betAmount;

    public Player(PlayerInput playerInput, List<Card> cards) {
        super(cards);
        this.name = playerInput.getPlayerName();
        this.betAmount = playerInput.getBetAmount();
    }

    public Player(String name, int betAmount, List<Card> cards) {
        super(cards);
        this.name = name;
        this.betAmount = betAmount;
    }

    public boolean neverHit() {
        return countHands() == INITIAL_DEAL_COUNT;
    }

    public String getName() {
        return name;
    }

    public int getBetAmount() {
        return betAmount;
    }
}
