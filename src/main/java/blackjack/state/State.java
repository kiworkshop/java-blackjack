package blackjack.state;

import blackjack.domain.Card;
import blackjack.domain.Cards;

import java.util.List;

public interface State {

    State hit(Cards cards);

    State stay();

    int getScore(Cards cards);
}
