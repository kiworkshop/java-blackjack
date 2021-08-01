package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.game.Hands;

import java.util.List;

public class Participant {
    protected final Hands hands;

    public Participant(List<Card> cards) {
        this.hands = new Hands(cards);
    }

    public void take(List<Card> cards) {
        hands.add(cards);
    }

    public int sumRank() {
        return hands.sumRanks();
    }

    public boolean bust() {
        return hands.bust();
    }

    public boolean blackjack() {
        return hands.blackjack();
    }

    public int countHands() {
        return hands.size();
    }

    public List<Card> getCards() {
        return hands.getHands();
    }
}