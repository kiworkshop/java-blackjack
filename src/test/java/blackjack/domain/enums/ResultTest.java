package blackjack.domain.enums;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class ResultTest {

    @ParameterizedTest
    @CsvSource(value = {"WIN, 1", "LOSE, -1", "DRAW, 0"})
    @DisplayName("게임 결과 포인트를 반환한다.")
    void getPoint(String resultName, int expectedPoint) {
        //given
        Result result = Result.valueOf(resultName);

        //when
        int point = result.getPoint();

        //then
        assertThat(point).isEqualTo(expectedPoint);
    }
}
