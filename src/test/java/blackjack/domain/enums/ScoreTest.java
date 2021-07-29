package blackjack.domain.enums;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreTest {

    @ParameterizedTest
    @CsvSource(value = {"A, 1", "TWO, 2", "THREE, 3", "FOUR, 4", "FIVE, 5", "SIX, 6", "SEVEN, 7", "EIGHT, 8", "NINE, 9", "TEN, 10", "J, 10", "Q, 10", "K, 10"})
    @DisplayName("카드의 점수를 반환한다.")
    void getScore(String scoreName, int expectedScore) {
        //given
        Score cardScore = Score.valueOf(scoreName);

        //when
        int score = cardScore.getScore();

        //then
        assertThat(score).isEqualTo(expectedScore);
    }

    @ParameterizedTest
    @CsvSource(value = {"A, A", "TWO, 2", "THREE, 3", "FOUR, 4", "FIVE, 5", "SIX, 6", "SEVEN, 7", "EIGHT, 8", "NINE, 9", "TEN, 10", "J, J", "Q, Q", "K, K"})
    @DisplayName("카드의 점수 이름을 반환한다.")
    void getDenomination(String scoreName, String expectedDenomiantion) {
        //given
        Score score = Score.valueOf(scoreName);

        //when
        String denomination = score.getDenomination();

        //then
        assertThat(denomination).isEqualTo(expectedDenomiantion);
    }

    @ParameterizedTest
    @CsvSource(value = {"A, true", "TWO, false", "TEN, false", "J, false"})
    @DisplayName("카드가 Ace일 경우 참을 반환한다.")
    void isAce(String scoreName, boolean expected) {
        //given
        Score score = Score.valueOf(scoreName);

        //when
        boolean actual = score.isAce();

        //then
        assertThat(actual).isEqualTo(expected);
    }
}
