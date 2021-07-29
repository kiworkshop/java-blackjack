package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.game.Hands;

import java.util.List;

public class Participant {
    protected final Hands hands;

    public Participant(Hands hands) {
        this.hands = hands;
    }

    public void take(Card card) {
        hands.addCard(card);
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