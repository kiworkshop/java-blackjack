package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.game.Hands;

import java.util.List;

import static blackjack.domain.prize.PrizeResults.BLACKJACK_RANK;

public class Participant implements BlackJackRule {
    protected final Hands hands;

    public Participant(Hands hands) {
        this.hands = hands;
    }

    @Override
    public void take(Card card) {
        hands.addCard(card);
    }

    @Override
    public int sumRank() {
        return hands.sumRanks();
    }

    @Override
    public boolean hit() {
        return hands.sumRanks() < BLACKJACK_RANK;
    }

    @Override
    public boolean bust() {
        return hands.sumRanks() > BLACKJACK_RANK;
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
    public List<Card> getCards() {
        return hands.getHands();
    }
}
