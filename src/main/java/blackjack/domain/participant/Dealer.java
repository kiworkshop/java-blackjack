package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.game.Hands;

public class Dealer extends Participant {
    private static final int HIT_THRESHOLD = 16;

    public Dealer(Hands hands) {
        super(hands);
    }

    public Card getFaceUpCard() {
        return hands.getFirstHand();
    }

    public boolean hit() {
        return sumRank() <= HIT_THRESHOLD;
    }
}
