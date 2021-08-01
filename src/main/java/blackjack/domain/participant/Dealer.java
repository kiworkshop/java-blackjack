package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.GivenCards;
import blackjack.domain.state.State;
import blackjack.domain.state.finished.Blackjack;
import blackjack.domain.state.finished.Stay;
import blackjack.domain.state.running.Hit;

public class Dealer extends Gamer {
    public static final int BLACKJACK = 21;
    private static final int HIT_THRESHOLD = 16;

    public Dealer(final String name, final GivenCards givenCards) {
        super(name);
        state = injectStateBy(givenCards);
    }

    @Override
    public State injectStateBy(final GivenCards cards) {
        if (cards.isBlackjack()) {
            return new Blackjack(cards);
        }

        if (cards.sum() <= HIT_THRESHOLD) {
            return new Hit(cards);
        }

        return new Stay(cards);
    }

    @Override
    public void hit(final Card card) {
        State newState = state.hit(card);
        int sum = newState.sum();

        if (sum > HIT_THRESHOLD && sum <= BLACKJACK) {
            state = new Stay(newState.getCards());
            return;
        }

        state = newState;
    }
}
