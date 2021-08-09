package blackjack.state;

import blackjack.domain.Card;
import blackjack.domain.Cards;

import java.util.List;

public class Bust implements State{
    @Override
    public State hit(Cards cards) {
        throw new RuntimeException("더이상 hit이 불가능합니다.");
    }


    @Override
    public State stay() {
        throw new RuntimeException("더이상 stay가 불가능합니다.");
    }

    @Override
    public int getScore(Cards cards) {
        return cards.getCardScore();
    }
}
