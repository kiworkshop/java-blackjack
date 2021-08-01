package blackjack.domain.state;

import blackjack.domain.Card;
import blackjack.domain.GivenCards;
import blackjack.domain.enums.Score;
import blackjack.domain.enums.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

class RunningTest {

    @Test
    @DisplayName("게임 진행 중 참여 종료 여부를 물어볼 경우, 거짓을 반환한다.")
    void isFinished() {
        //given
        State runningState = createRunningState();

        //when
        boolean finished = runningState.isFinished();

        //then
        assertThat(finished).isFalse();
    }

    private State createRunningState() {
        GivenCards givenCards = new GivenCards(Collections.singletonList(new Card(Score.A, Suit.CLUB)));

        return new Running(givenCards) {
            @Override
            public State hit(Card card) {
                return null;
            }

            @Override
            public State stay() {
                return null;
            }

            @Override
            public GivenCards getCards() {
                return null;
            }
        };
    }
}
