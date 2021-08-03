package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.GivenCards;
import blackjack.domain.enums.Score;
import blackjack.domain.enums.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class InitTest {

    @Test
    @DisplayName("모든 카드 목록을 반환한다.")
    void getCards() {
        ///given
        Card card1 = new Card(Score.TEN, Suit.CLUB);
        Card card2 = new Card(Score.TEN, Suit.HEART);
        GivenCards givenCards = new GivenCards(Arrays.asList(card1, card2));
        State init = createInitState(givenCards);

        //when
        GivenCards cards = init.getCards();

        //then
        assertThat(cards).isEqualTo(givenCards);
    }

    @Test
    @DisplayName("게임 진행 중 카드의 합을 요청할 경우, 예외가 발생한다.")
    void sum() {
        ///given
        Card card1 = new Card(Score.TEN, Suit.CLUB);
        Card card2 = new Card(Score.TEN, Suit.HEART);
        GivenCards givenCards = new GivenCards(Arrays.asList(card1, card2));
        State init = createInitState(givenCards);

        //when
        int sum = init.sum();

        //then
        assertThat(sum).isEqualTo(20);
    }

    private Init createInitState(GivenCards givenCards) {
        return new Init(givenCards) {
            @Override
            public int compare(GivenCards givenCards) {
                return 0;
            }

            @Override
            public State hit(Card card) {
                return null;
            }

            @Override
            public State stay() {
                return null;
            }

            @Override
            public boolean isFinished() {
                return false;
            }
        };
    }
}
