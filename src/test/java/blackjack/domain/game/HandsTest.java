package blackjack.domain.game;

import blackjack.domain.card.AceCard;
import blackjack.domain.card.Card;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static blackjack.domain.card.TestCard.*;
import static org.assertj.core.api.Assertions.assertThat;

class HandsTest {
    @Test
    @DisplayName("에이스 카드가 없을 때 전체 카드 합을 계산한다.")
    void sum_hands_without_ace_card() {
        //given
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Suit.DIAMOND, 1));
        cards.add(new Card(Suit.DIAMOND, "K"));

        //when
        Hands hands = new Hands(cards);
        int sum = hands.sumRanks();

        //then
        assertThat(sum).isEqualTo(11);
    }


    @Test
    @DisplayName("에이스 카드가 하나 있을 때 전체 카드 합을 계산한다.")
    void sum_hands_with_one_ace_card() {
        //given
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Suit.DIAMOND, 1));
        cards.add(new Card(Suit.DIAMOND, "K"));
        cards.add(new AceCard(Suit.DIAMOND));

        //when
        Hands hands = new Hands(cards);
        int sum = hands.sumRanks();

        //then
        assertThat(sum).isEqualTo(12);
    }

    @ParameterizedTest
    @MethodSource({"generateHands"})
    @DisplayName("에이스 카드 개수와 나머지 카드 합이 주어지면 1 또는 11 값을 반환한다.")
    void several_ace_cards(Hands hands, int exceptedRankSum) {
        //given, when
        int cardRankSum = hands.sumRanks();

        // then
        assertThat(cardRankSum).isEqualTo(exceptedRankSum);
    }

    public static Stream<Arguments> generateHands() {
        return Stream.of(
                Arguments.of(new Hands(Arrays.asList(CARD_4, CARD_5, ACE_1, ACE_2)), 21),
                Arguments.of(new Hands(Arrays.asList(CARD_9, CARD_J, ACE_1, ACE_2)), 21),
                Arguments.of(new Hands(Arrays.asList(CARD_Q, CARD_J, ACE_1, ACE_2)), 22),

                Arguments.of(new Hands(Arrays.asList(CARD_3, CARD_5, ACE_1, ACE_2, ACE_3)), 21),
                Arguments.of(new Hands(Arrays.asList(CARD_8, CARD_K, ACE_1, ACE_2, ACE_3)), 21),
                Arguments.of(new Hands(Arrays.asList(CARD_9, CARD_J, ACE_1, ACE_2, ACE_3)), 22),

                Arguments.of(new Hands(Arrays.asList(CARD_3, CARD_4, ACE_1, ACE_2, ACE_3, ACE_4)), 21),
                Arguments.of(new Hands(Arrays.asList(CARD_7, CARD_K, ACE_1, ACE_2, ACE_3, ACE_4)), 21),
                Arguments.of(new Hands(Arrays.asList(CARD_8, CARD_K, ACE_1, ACE_2, ACE_3, ACE_4)), 22)
        );
    }
}
