package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.game.Hands;

import java.util.List;

import static blackjack.domain.prize.PrizeResults.BLACKJACK_RANK;

public class Participant implements BlackjackRule {
    protected final Hands hands;

    public Participant(Hands hands) {
        this.hands = hands;
    }

    @Override
    public void draw(Card card) {
        hands.addCard(card);
    }

    @Override
    public boolean hit() {
        return hands.sumRank() < BLACKJACK_RANK;
    }

    @Override
    public boolean bust() {
        return hands.sumRank() > BLACKJACK_RANK;
    }

    @Override
    public boolean blackjack() {
        return hands.countAceCards() == 1 && hands.countMajorCards() == 1;
    }

    @Override
    public int countHands() {
        return hands.size();
    }

    @Override
    public List<Card> cards() {
        return hands.cards();
    }

    public Hands hands() {
        return hands;
    }
}
