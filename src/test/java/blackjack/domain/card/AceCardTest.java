package blackjack.domain.card;

import blackjack.domain.game.Hands;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static blackjack.domain.card.TestCard.*;
import static org.assertj.core.api.Assertions.assertThat;

class AceCardTest {

    @Test
    @DisplayName("에이스 카드가 하나 있을 때 전체 카드 합을 계산한다.")
    void sum_hands_with_one_ace_card() {
        //given
        List<Card> cards = new ArrayList<>();
        cards.add(CARD_1);
        cards.add(CARD_K);
        cards.add(ACE_1);

        //when
        Hands hands = new Hands(cards);
        int sum = hands.sumRanks();

        //then
        assertThat(sum).isEqualTo(12);
    }

    @ParameterizedTest
    @MethodSource({"generateHands"})
    @DisplayName("에이스 카드 개수와 나머지 카드 합이 주어지면 1 또는 11 값을 반환한다.")
    void several_ace_cards(int sumExceptAceCards, int aceCardCount, int exceptedRankSum) {
        //given, when
        int cardRankSum = AceCard.softOrHardSum(sumExceptAceCards, aceCardCount);

        // then
        assertThat(cardRankSum).isEqualTo(exceptedRankSum);
    }

    private static Stream<Arguments> generateHands() {
        return Stream.of(
                Arguments.of(9, 2, 21),
                Arguments.of(19, 2, 21),
                Arguments.of(20, 2, 22),
                Arguments.of(8, 3, 21),
                Arguments.of(18, 3, 21),
                Arguments.of(19, 3, 22),
                Arguments.of(7, 4, 21),
                Arguments.of(17, 4, 21),
                Arguments.of(18, 4, 22)
        );
    }
}