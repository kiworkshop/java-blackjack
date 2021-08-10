package blackjack.state;

import blackjack.domain.Cards;

public interface State {

    State hit(Cards cards);

    State stay();

    int getScore(Cards cards);
}
