package blackjack.domain.game;

import blackjack.domain.card.Card;
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
        cards.add(CARD_1);
        cards.add(CARD_K);

        //when
        Hands hands = new Hands(cards);
        int sum = hands.sumRanks();

        //then
        assertThat(sum).isEqualTo(11);
    }

    @Test
    @DisplayName("블랙잭인 것을 확인한다.")
    void blackjack() {
        // given
        Hands blackjackHands = new Hands(Arrays.asList(ACE_1, CARD_Q));
        Hands notBlackjackHands = new Hands(Arrays.asList(ACE_1, CARD_9));

        // when
        boolean blackjack = blackjackHands.blackjack();
        boolean notBlackjack = notBlackjackHands.blackjack();

        // then
        assertThat(blackjack).isTrue();
        assertThat(notBlackjack).isFalse();
    }

    @Test
    @DisplayName("버스트인 것을 확인한다.")
    void bust() {
        // given
        Hands bustHands = new Hands(Arrays.asList(CARD_3, CARD_K, CARD_Q));
        Hands notBustHands = new Hands(Arrays.asList(ACE_1, CARD_9));

        // when
        boolean bust = bustHands.bust();
        boolean notBust = notBustHands.bust();

        // then
        assertThat(bust).isTrue();
        assertThat(notBust).isFalse();
    }
}
