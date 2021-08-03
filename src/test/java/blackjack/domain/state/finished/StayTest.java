package blackjack.domain.state.finished;

import blackjack.domain.card.Card;
import blackjack.domain.card.GivenCards;
import blackjack.domain.enums.Score;
import blackjack.domain.enums.Suit;
import blackjack.domain.state.State;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class StayTest {

    @Test
    @DisplayName("다른 카드의 합보다 클 경우, 승리를 의미하는 1을 반환한다.")
    void result_win() {
        //given
        Card card1 = new Card(Score.EIGHT, Suit.CLUB);
        Card card2 = new Card(Score.EIGHT, Suit.HEART);
        GivenCards myCards = new GivenCards(Arrays.asList(card1, card2));
        State stay = new Stay(myCards);
        Card card3 = new Card(Score.SEVEN, Suit.DIAMOND);
        GivenCards otherCards = new GivenCards(Arrays.asList(card1, card3));

        //when
        int result = stay.compare(otherCards);

        //then
        assertThat(result).isEqualTo(1);
    }

    @Test
    @DisplayName("다른 카드의 합보다 작을 경우, 패배를 의미하는 -1을 반환한다.")
    void result_lose() {
        //given
        Card card1 = new Card(Score.EIGHT, Suit.CLUB);
        Card card2 = new Card(Score.EIGHT, Suit.HEART);
        GivenCards myCards = new GivenCards(Arrays.asList(card1, card2));
        State stay = new Stay(myCards);
        Card card3 = new Card(Score.NINE, Suit.DIAMOND);
        GivenCards otherCards = new GivenCards(Arrays.asList(card1, card3));

        //when
        int result = stay.compare(otherCards);

        //then
        assertThat(result).isEqualTo(-1);
    }

    @Test
    @DisplayName("다른 카드의 합과 같은 경우, 무승부를 의미하는 0을 반환한다.")
    void result_draw() {
        //given
        Card card1 = new Card(Score.EIGHT, Suit.CLUB);
        Card card2 = new Card(Score.EIGHT, Suit.HEART);
        GivenCards myCards = new GivenCards(Arrays.asList(card1, card2));
        State stay = new Stay(myCards);
        GivenCards otherCards = new GivenCards(Arrays.asList(card1, card2));

        //when
        int result = stay.compare(otherCards);

        //then
        assertThat(result).isZero();
    }
}
