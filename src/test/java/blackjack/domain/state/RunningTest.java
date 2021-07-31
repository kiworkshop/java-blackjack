package blackjack.domain.state;

import blackjack.domain.Card;
import blackjack.domain.GivenCards;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RunningTest {

    @Test
    @DisplayName("게임 진행 중 카드의 합을 요청할 경우, 예외가 발생한다.")
    void sum() {
        //given
        State runningState = createRunningState();

        //when //then
        assertThatThrownBy(runningState::sum)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("게임 진행 중에는 카드의 합을 반환할 수 없습니다.");
    }

    @Test
    @DisplayName("게임 진행 중 게임 종료 여부를 물어볼 경우, 거짓을 반환한다.")
    void isFinished() {
        //given
        State runningState = createRunningState();

        //when
        boolean finished = runningState.isFinished();

        //then
        assertThat(finished).isFalse();
    }

    private State createRunningState() {
        return new Running() {
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
