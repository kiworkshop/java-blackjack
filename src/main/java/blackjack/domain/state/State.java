package blackjack.domain.state;

import blackjack.domain.Card;
import blackjack.domain.GivenCards;

public interface State {

    State hit(Card card);

    State stay();

    int sum();

    boolean isFinished();

    GivenCards getCards();

    int result(GivenCards givenCards);
}
