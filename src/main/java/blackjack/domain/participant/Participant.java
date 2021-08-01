package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.game.Hands;

import java.util.List;

public abstract class Participant {
    protected final Hands hands;

    protected Participant(List<Card> cards) {
        this.hands = new Hands(cards);
    }

    public void take(List<Card> cards) {
        hands.add(cards);
    }

    public int sumRank() {
        return hands.sumRanks();
    }

    public int countAceCards() {
        return hands.countAceCards();
    }

    public int countMajorCards() {
        return hands.countMajorCards();
    }

    public int countHands() {
        return hands.size();
    }

    public List<Card> getCards() {
        return hands.getHands();
    }
}