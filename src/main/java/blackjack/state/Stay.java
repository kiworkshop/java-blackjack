package blackjack.state;

import blackjack.domain.Cards;

public class Stay implements State {
    @Override
    public State hit(Cards cards) {
        throw new RuntimeException("더이상 hit이 불가능합니다.");
    }

    @Override
    public State stay() {
        throw new RuntimeException("이미 stay상태 입니다.");
    }

    @Override
    public int getScore(Cards cards) {
        return cards.getCardScore();
    }
}
