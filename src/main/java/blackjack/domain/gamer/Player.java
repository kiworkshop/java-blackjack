package blackjack.domain.gamer;

import blackjack.domain.game.Hands;

import static blackjack.domain.card.Deck.INITIAL_DEAL_COUNT;
import static blackjack.domain.prize.PrizeResults.BLACKJACK_RANK;

public class Player extends Gamer {
    public Player(String name, Hands hands) {
        super(name, hands);
    }

    @Override
    public boolean canHit() {
        return this.sumRank() < BLACKJACK_RANK;
    }

    public boolean neverHit() {
        return this.countHands() == INITIAL_DEAL_COUNT;
    }

}
