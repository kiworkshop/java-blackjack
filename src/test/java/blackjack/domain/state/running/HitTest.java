package blackjack.domain.state.running;

import blackjack.domain.card.Card;
import blackjack.domain.card.GivenCards;
import blackjack.domain.enums.Score;
import blackjack.domain.enums.Suit;
import blackjack.domain.state.State;
import blackjack.domain.state.finished.Burst;
import blackjack.domain.state.finished.Stay;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class HitTest {

    @Test
    @DisplayName("지급받은 카드 포함 합이 21을 초과할 경우, Burst 상태를 반환한다.")
    void hit_return_burst_state() {
        //given
        Card card1 = Card.from(Score.TEN, Suit.CLUB);
        Card card2 = Card.from(Score.TEN, Suit.HEART);
        GivenCards givenCards = new GivenCards(Arrays.asList(card1, card2));
        State hit = new Hit(givenCards);
        Card newCard = Card.from(Score.TWO, Suit.CLUB);

        //when
        State state = hit.hit(newCard);

        //then
        assertThat(state).isInstanceOf(Burst.class);
    }

    @Test
    @DisplayName("지급받은 카드 포함 합이 21 미만일 경우, Hit 상태를 반환한다.")
    void hit_return_itself() {
        //given
        Card card1 = Card.from(Score.TEN, Suit.CLUB);
        Card card2 = Card.from(Score.NINE, Suit.HEART);
        GivenCards givenCards = new GivenCards(Arrays.asList(card1, card2));
        State hit = new Hit(givenCards);
        Card newCard = Card.from(Score.A, Suit.CLUB);

        //when
        State state = hit.hit(newCard);

        //then
        assertThat(state).isInstanceOf(Hit.class);
    }

    @Test
    @DisplayName("지급받은 카드 포함 합이 21일 경우, Stay 상태를 반환한다.")
    void hit_return_stay_state() {
        //given
        Card card1 = Card.from(Score.TEN, Suit.CLUB);
        Card card2 = Card.from(Score.NINE, Suit.HEART);
        GivenCards givenCards = new GivenCards(Arrays.asList(card1, card2));
        State hit = new Hit(givenCards);
        Card newCard = Card.from(Score.TWO, Suit.CLUB);

        //when
        State state = hit.hit(newCard);

        //then
        assertThat(state).isInstanceOf(Stay.class);
    }

    @Test
    @DisplayName("Stay 상태를 반환한다.")
    void stay() {
        ///given
        Card card1 = Card.from(Score.TEN, Suit.CLUB);
        Card card2 = Card.from(Score.TEN, Suit.HEART);
        GivenCards givenCards = new GivenCards(Arrays.asList(card1, card2));
        State hit = new Hit(givenCards);

        //when
        State state = hit.stay();

        //then
        assertThat(state).isInstanceOf(Stay.class);
    }
}
