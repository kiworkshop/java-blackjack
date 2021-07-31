package blackjack.domain.state;

import blackjack.domain.Card;
import blackjack.domain.GivenCards;

public class Started extends Running {
    private final GivenCards givenCards;

    public Started(GivenCards givenCards) {
        this.givenCards = givenCards;
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
        return new Stay(givenCards);
    }

    @Override
    public GivenCards getCards() {
        return givenCards;
    }
}
