package blackjack.domain.participant;

import blackjack.domain.GivenCards;
import blackjack.domain.state.State;
import blackjack.domain.state.finished.Stay;

public abstract class Gamer implements Person {
    private final String name;
    protected State state;

    protected Gamer(final String name) {
        this.name = name;
    }

    @Override
    public GivenCards getCards() {
        return state.getCards();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int sum() {
        return state.sum();
    }

    @Override
    public void stay() {
        state = new Stay(getCards());
    }

    @Override
    public boolean isFinished() {
        return state.isFinished();
    }
}
