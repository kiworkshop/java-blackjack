package blackjack.domain.state.running;

import blackjack.domain.card.Card;
import blackjack.domain.card.GivenCards;
import blackjack.domain.enums.Score;
import blackjack.domain.enums.Suit;
import blackjack.domain.state.State;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

    @Test
    @DisplayName("게임 진행 중 게임 결과를 요청할 경우, 예외가 발생한다.")
    void result() {
        //given
        State runningState = createRunningState();

        //when //then
        assertThatThrownBy(() -> runningState.compare(new GivenCards(Collections.emptyList())))
                .hasMessage("게임 진행 중에는 결과를 알 수 없습니다.");
    }

    private State createRunningState() {
        GivenCards givenCards = new GivenCards(Collections.singletonList(Card.from(Score.A, Suit.CLUB)));

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
