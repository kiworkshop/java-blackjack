package blackjack.domain.gamer;

import blackjack.domain.game.Hands;

public class Dealer extends Gamer {
    private static final String DEALER_NAME = "딜러";
    private static final int HIT_THRESHOLD = 16;

    public Dealer(Hands hands) {
        super(DEALER_NAME, hands);
    }

    @Override
    public boolean canHit() {
        return this.sumRank() <= HIT_THRESHOLD;
    }
}
