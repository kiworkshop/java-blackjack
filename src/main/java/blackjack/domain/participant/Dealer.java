package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.game.Hands;

import java.util.List;

public class Dealer {
    private static final int HIT_THRESHOLD = 16;

    private final Hands hands;

    public Dealer(Hands hands) {
        this.hands = hands;
    }

    public Card getFaceUpCard() {
        return hands.getFirstHand();
    }

    public boolean hit() {
        return hands.sumRanks() <= HIT_THRESHOLD;
    }

    public boolean bust() {
        return hands.bust();
    }

    public void take(Card card) {
        hands.addCard(card);
    }

    public int sumRank() {
        return hands.sumRanks();
    }

    public int countHands() {
        return hands.size();
    }

    public List<Card> getCards() {
        return hands.getHands();
    }

    public boolean blackjack() {
        return hands.blackjack();
    }
}
