package blackjack.domain.prize;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class ProfitTest {

    @ParameterizedTest
    @MethodSource("generatePrizeAndExpectedResult")
    @DisplayName("배팅 금액과 승패 결과가 주어지면 상금을 계산한다.")
    void calculate_profit(int betAmount, Prize prize, int expectedReceiveAmount, int expectedProfit) {
        // given, when
        Profit profit = new Profit(betAmount, prize);

        // then
        assertThat(profit.getReceiveAmount()).isEqualTo(expectedReceiveAmount);
        assertThat(profit.getProfit()).isEqualTo(expectedProfit);
    }

    private static Stream<Arguments> generatePrizeAndExpectedResult() {
        return Stream.of(
                Arguments.of(100, Prize.BLACKJACK, 250, 150),
                Arguments.of(100, Prize.WIN, 200, 100),
                Arguments.of(100, Prize.TIE, 100, 0),
                Arguments.of(100, Prize.LOSE, 0, -100)
        );
    }
}