package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class SignatureTest {

    @ParameterizedTest
    @MethodSource({"generateHands"})
    @DisplayName("에이스 카드 개수와 나머지 카드 합이 주어지면 에이스 카드 합을 반환한다.")
    void several_ace_cards(int sumExceptAceCards, int aceCardCount, int exceptedAceSum) {
        //given, when
        int cardRankSum = Signature.sumAceCards(sumExceptAceCards, aceCardCount);

        // then
        assertThat(cardRankSum).isEqualTo(exceptedAceSum);
    }

    private static Stream<Arguments> generateHands() {
        return Stream.of(
                Arguments.of(9, 2, 12),
                Arguments.of(19, 2, 2),
                Arguments.of(20, 2, 2),
                Arguments.of(8, 3, 13),
                Arguments.of(18, 3, 3),
                Arguments.of(19, 3, 3),
                Arguments.of(7, 4, 14),
                Arguments.of(17, 4, 4),
                Arguments.of(18, 4, 4)
        );
    }
}