package blackjack.domain.state;

import blackjack.domain.Card;

public abstract class Finished implements State {

    @Override
    public State hit(Card card) {
        throw new IllegalStateException("참여 종료 후에는 추가 카드 요청을 할 수 없습니다.");
    }

    @Override
    public State stay() {
        throw new IllegalStateException("참여 종료 후에는 추가 카드 요청 거부를 할 수 없습니다.");
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
