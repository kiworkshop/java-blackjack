package blackjack.domain.state;

import blackjack.domain.GivenCards;

public abstract class Init implements State {
    private final GivenCards givenCards;

    protected Init(final GivenCards givenCards) {
        this.givenCards = givenCards;
    }

    @Override
    public GivenCards getCards() {
        return givenCards;
    }

    @Override
    public int sum() {
        return givenCards.sum();
    }
}
