package blackjack.domain.state;

import blackjack.domain.Card;
import blackjack.domain.GivenCards;

public class Hit extends Running {

    public Hit(final GivenCards givenCards) {
        super(givenCards);
    }

    @Override
    public State hit(final Card card) {
        GivenCards givenCards = getCards();
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
