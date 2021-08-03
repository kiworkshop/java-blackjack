package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.GivenCards;
import blackjack.domain.state.State;
import blackjack.domain.state.finished.Blackjack;
import blackjack.domain.state.finished.Burst;
import blackjack.domain.state.finished.Stay;
import blackjack.domain.state.running.Hit;

public class Player extends Gamer {

    public Player(final String name, final GivenCards cards) {
        super(name);
        state = injectStateBy(cards);
    }

    @Override
    public State injectStateBy(final GivenCards cards) {
        if (cards.isBlackjack()) {
            return new Blackjack(cards);
        }

        if (cards.isBurst()) {
            return new Burst(cards);
        }

        if (cards.isMaximumThreshold()) {
            return new Stay(cards);
        }

        return new Hit(cards);
    }

    @Override
    public void hit(final Card card) {
        state = state.hit(card);
    }
}
