package blackjack.domain;

import blackjack.domain.participant.Participant;
import blackjack.domain.state.State;
import blackjack.domain.state.finished.Blackjack;
import blackjack.domain.state.finished.Stay;
import blackjack.domain.state.running.Hit;

public class Player implements Participant {
    private final String name;
    private State state;

    public Player(final String name, final GivenCards cards) {
        this.name = name;
        initState(cards);
    }

    private void initState(final GivenCards cards) {
        if (cards.isBlackjack()) {
            state = new Blackjack(cards);
            return;
        }

        state = new Hit(cards);
    }

    @Override
    public void hit(final Card card) {
        state = state.hit(card);
    }

    @Override
    public void stay() {
        state = new Stay(getCards());
    }

    @Override
    public GivenCards getCards() {
        return state.getCards();
    }

    @Override
    public int sum() {
        return state.sum();
    }
}
