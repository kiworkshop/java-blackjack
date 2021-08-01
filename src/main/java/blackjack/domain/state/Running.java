package blackjack.domain.state;

import blackjack.domain.GivenCards;

public abstract class Running extends Init {

    Running(final GivenCards givenCards) {
        super(givenCards);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
