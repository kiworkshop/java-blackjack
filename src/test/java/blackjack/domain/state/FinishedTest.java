package blackjack.domain.state;

import blackjack.domain.Card;
import blackjack.domain.GivenCards;
import blackjack.domain.enums.Score;
import blackjack.domain.enums.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FinishedTest {

    @Test
    @DisplayName("참여 종료 상태에서 카드 발급 요청을 할 경우 예외가 발생한다.")
    void hit() {
        //given
        State finishedState = createFinishedState();
        Card card = new Card(Score.A, Suit.CLUB);

        //when //then
        assertThatThrownBy(() -> finishedState.hit(card))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("참여 종료 후에는 추가 카드 요청을 할 수 없습니다.");
    }

    @Test
    @DisplayName("참여 종료 상태에서 카드 발급 거부를 할 경우 예외가 발생한다.")
    void stay() {
        //given
        State finishedState = createFinishedState();

        //when //then
        assertThatThrownBy(finishedState::stay)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("참여 종료 후에는 추가 카드 요청 거부를 할 수 없습니다.");
    }

    @Test
    @DisplayName("참여 종료 상태에서 참여 종료 여부를 물어볼 경우, 참을 반환한다.")
    void isFinished() {
        //given
        State finishedState = createFinishedState();

        //when
        boolean finished = finishedState.isFinished();

        //then
        assertThat(finished).isTrue();
    }

    private State createFinishedState() {
        GivenCards givenCards = new GivenCards(Collections.singletonList(new Card(Score.A, Suit.CLUB)));

        return new Finished(givenCards) {
            @Override
            public int sum() {
                return 0;
            }

            @Override
            public GivenCards getCards() {
                return null;
            }
        };
    }


}
