package blackjack.domain.state.running;

import blackjack.domain.card.Card;
import blackjack.domain.card.GivenCards;
import blackjack.domain.state.State;
import blackjack.domain.state.finished.Burst;
import blackjack.domain.state.finished.Stay;

public class Hit extends Running {

    public Hit(final GivenCards givenCards) {
        super(givenCards);
    }

    @Override
    public State hit(final Card card) {
        givenCards.add(card);

        if (givenCards.isBurst()) {
            return new Burst(givenCards);
        }

        return new Hit(givenCards);
    }

    @Override
    public State stay() {
        return new Stay(getCards());
    }
}
