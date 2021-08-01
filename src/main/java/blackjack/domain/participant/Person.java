package blackjack.domain.participant;

import blackjack.domain.Card;
import blackjack.domain.GivenCards;
import blackjack.domain.state.State;

public interface Person {

    State injectStateBy(GivenCards cards);

    void hit(Card card);

    GivenCards getCards();

    String getName();

    int sum();

    void stay();

    boolean isFinished();
}
