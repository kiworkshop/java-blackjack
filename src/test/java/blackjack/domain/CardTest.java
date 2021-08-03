package blackjack.domain;

import blackjack.enums.Denomination;
import blackjack.enums.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class CardTest {

    @ParameterizedTest
    @CsvSource(value = {"TWO, 2", "THREE, 3", "FOUR, 4", "FIVE, 5", "SIX, 6", "A, 1", "K, 10", "Q, 10", "J, 10"})
    @DisplayName("카드의 점수를 반환한다.")
    void getScore(String inputScore, int expectedValue) {
        //given
        Card card = new Card(Denomination.valueOf(inputScore), Suit.DIAMOND);

        //when
        int score = card.getScore();

        //then
        assertThat(score).isEqualTo(expectedValue);
    }

}