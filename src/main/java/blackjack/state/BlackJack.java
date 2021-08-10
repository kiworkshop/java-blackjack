package blackjack.state;

import blackjack.domain.Cards;

public class BlackJack implements State {
    @Override
    public State hit(Cards cards) {
        throw new RuntimeException("BlackJack에선 추가 카드를 가져올 수 없습니다.");
    }

    @Override
    public State stay() {
        throw new RuntimeException("BlackJack에선 stay를 할 수 없습니다.");
    }

    @Override
    public int getScore(Cards cards) {
        return cards.getCardScore();
    }
}
