package blackjack.domain.state.running;

import blackjack.domain.card.GivenCards;
import blackjack.domain.state.Init;

public abstract class Running extends Init {

    Running(final GivenCards givenCards) {
        super(givenCards);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public int compare(GivenCards givenCards) {
        throw new IllegalStateException("게임 진행 중에는 결과를 알 수 없습니다.");
    }
}
