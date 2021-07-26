package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.game.Hands;

public class Dealer {
    private static final int ADD_HAND_THRESHOLD = 16;

    private final Hands hands;

    public Dealer(Hands hands) {
        this.hands = hands;
    }

    public int countHands() {
        return hands.size();
    }

    public Card getFaceUpCard() {
        return hands.getFirstHand();
    }

    public Hands finalDeal(Card card) {
        if (hands.sumRanks() < ADD_HAND_THRESHOLD) {
            hands.addCard(card);
        }
        return this.hands;
    }
}
