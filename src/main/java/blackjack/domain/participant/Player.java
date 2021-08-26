package blackjack.domain.participant;

import blackjack.domain.game.Hands;

import static blackjack.domain.card.Deck.INITIAL_DEAL_COUNT;

public class Player extends Participant {
    private final String name;

    public Player(String name, Hands hands) {
        super(hands);
        this.name = name;
    }

    public boolean neverHit() {
        return this.countHands() == INITIAL_DEAL_COUNT;
    }

    public String getName() {
        return name;
    }
}
