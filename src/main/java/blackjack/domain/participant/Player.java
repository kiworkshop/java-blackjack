package blackjack.domain.participant;

import blackjack.domain.Card;
import blackjack.domain.GivenCards;
import blackjack.domain.state.State;
import blackjack.domain.state.finished.Blackjack;
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

        return new Hit(cards);
    }

    @Override
    public void hit(final Card card) {
        state = state.hit(card);
    }
}
