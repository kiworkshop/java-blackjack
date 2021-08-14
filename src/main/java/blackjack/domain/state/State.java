package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.GivenCards;

public interface State {

    State hit(Card card);

    State stay();

    int sum();

    boolean isFinished();

    GivenCards getCards();

    int compare(GivenCards givenCards);
}
