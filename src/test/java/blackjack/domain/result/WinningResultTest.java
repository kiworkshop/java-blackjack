package blackjack.domain.result;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

class WinningResultTest {

    @ParameterizedTest
    @MethodSource("createWinningResult")
    @DisplayName("승패 결과를 가져온다.")
    void getWinningResultTest(WinningResult winningResult, String expected) {
        //when //then
        assertThat(winningResult.getResult()).isEqualTo(expected);
    }

    private static Stream<Arguments> createWinningResult() {
        return Stream.of(
                Arguments.of(WinningResult.WIN, "승"),
                Arguments.of(WinningResult.LOSE, "패"),
                Arguments.of(WinningResult.TIE, "무승부")
        );
    }

    @ParameterizedTest
    @MethodSource("createReverseWinningResult")
    @DisplayName("승패 결과의 반전된 값을 가져온다.")
    void getWinningResultReverseTest(WinningResult winningResult, WinningResult expected) {
        //when //then
        assertThat(winningResult.reverse()).isEqualTo(expected);
    }

    private static Stream<Arguments> createReverseWinningResult() {
        return Stream.of(
                Arguments.of(WinningResult.WIN, WinningResult.LOSE),
                Arguments.of(WinningResult.LOSE, WinningResult.WIN),
                Arguments.of(WinningResult.TIE, WinningResult.TIE)
        );
    }
}