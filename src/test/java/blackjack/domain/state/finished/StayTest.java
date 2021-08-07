package blackjack.domain.state.finished;

import blackjack.domain.card.Card;
import blackjack.domain.card.GivenCards;
import blackjack.domain.enums.Score;
import blackjack.domain.enums.Suit;
import blackjack.domain.state.State;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class StayTest {

    @ParameterizedTest
    @CsvSource(value = {"FIVE, FOUR", "TEN, TWO"})
    @DisplayName("다른 카드의 합보다 크거나 다른 카드의 합이 21을 초과할 경우, 승리를 의미하는 1을 반환한다.")
    void result_win(String score1, String score2) {
        //given
        Card card1 = Card.from(Score.TEN, Suit.CLUB);
        Card card2 = Card.from(Score.TEN, Suit.HEART);
        GivenCards myCards = new GivenCards(Arrays.asList(card1, card2));
        State stay = new Stay(myCards);
        Card card3 = Card.from(Score.TEN, Suit.DIAMOND);
        Card card4 = Card.from(Score.valueOf(score1), Suit.CLUB);
        Card card5 = Card.from(Score.valueOf(score2), Suit.CLUB);
        GivenCards otherCards = new GivenCards(Arrays.asList(card3, card4, card5));

        //when
        int result = stay.compare(otherCards);

        //then
        assertThat(result).isEqualTo(1);
    }

    @Test
    @DisplayName("다른 카드의 합보다 작을 경우, 패배를 의미하는 -1을 반환한다.")
    void result_lose() {
        //given
        Card card1 = Card.from(Score.EIGHT, Suit.CLUB);
        Card card2 = Card.from(Score.EIGHT, Suit.HEART);
        GivenCards myCards = new GivenCards(Arrays.asList(card1, card2));
        State stay = new Stay(myCards);
        Card card3 = Card.from(Score.NINE, Suit.DIAMOND);
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
        Card card1 = Card.from(Score.EIGHT, Suit.CLUB);
        Card card2 = Card.from(Score.EIGHT, Suit.HEART);
        GivenCards myCards = new GivenCards(Arrays.asList(card1, card2));
        State stay = new Stay(myCards);
        GivenCards otherCards = new GivenCards(Arrays.asList(card1, card2));

        //when
        int result = stay.compare(otherCards);

        //then
        assertThat(result).isZero();
    }
}
