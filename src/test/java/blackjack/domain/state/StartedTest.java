package blackjack.domain.state;

import blackjack.domain.Card;
import blackjack.domain.GivenCards;
import blackjack.domain.enums.Score;
import blackjack.domain.enums.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class StartedTest {

    @Test
    @DisplayName("지급받은 카드 포함 합이 21을 초과할 경우, Burst 상태를 반환한다.")
    void hit_return_burst_state() {
        //given
        Card card1 = new Card(Score.TEN, Suit.CLUB);
        Card card2 = new Card(Score.TEN, Suit.HEART);
        GivenCards givenCards = new GivenCards(Arrays.asList(card1, card2));
        Started started = new Started(givenCards);
        Card newCard = new Card(Score.TWO, Suit.CLUB);

        //when
        State state = started.hit(newCard);

        //then
        assertThat(state).isInstanceOf(Burst.class);
    }

    @Test
    @DisplayName("지급받은 카드 포함 합이 21을 초과하지 않을 경우, Hit 상태를 반환한다.")
    void hit_return_itself() {
        //given
        Card card1 = new Card(Score.TEN, Suit.CLUB);
        Card card2 = new Card(Score.TEN, Suit.HEART);
        GivenCards givenCards = new GivenCards(Arrays.asList(card1, card2));
        State started = new Started(givenCards);
        Card newCard = new Card(Score.A, Suit.CLUB);

        //when
        State state = started.hit(newCard);

        //then
        assertThat(state).isInstanceOf(Hit.class);
    }

    @Test
    @DisplayName("Stay 상태를 반환한다.")
    void stay() {
        ///given
        Card card1 = new Card(Score.TEN, Suit.CLUB);
        Card card2 = new Card(Score.TEN, Suit.HEART);
        GivenCards givenCards = new GivenCards(Arrays.asList(card1, card2));
        State started = new Started(givenCards);

        //when
        State state = started.stay();

        //then
        assertThat(state).isInstanceOf(Stay.class);
    }

    @Test
    @DisplayName("모든 카드 목록을 반환한다.")
    void getCards() {
        ///given
        Card card1 = new Card(Score.TEN, Suit.CLUB);
        Card card2 = new Card(Score.TEN, Suit.HEART);
        GivenCards givenCards = new GivenCards(Arrays.asList(card1, card2));
        State started = new Started(givenCards);

        //when
        GivenCards cards = started.getCards();

        //then
        assertThat(cards).isEqualTo(givenCards);
    }
}
