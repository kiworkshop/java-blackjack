package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.game.Hands;

import static blackjack.domain.prize.PrizeResults.BLACKJACK_RANK;

public abstract class Gamer {
    private final String name;
    private final Hands hands;

    protected Gamer(String name, Hands hands) {
        this.name = name;
        this.hands = hands;
    }

    public abstract boolean canHit();

    public void hit(Card card) {
        hands.addCard(card);
    }

    public boolean isBust() {
        return sumRank() > BLACKJACK_RANK;
    }

    public int sumRank() {
        return hands.sumRank();
    }

    public boolean isBlackjack() {
        return hands.countAceCards() == 1 && hands.countMajorCards() == 1;
    }

    public Card getFaceUpCard() {
        return hands.firstHand();
    }

    public int countHands() {
        return hands.size();
    }

    public String name() {
        return name;
    }

    public Hands hands() {
        return hands;
    }
}
