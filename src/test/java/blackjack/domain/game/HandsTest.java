package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class HandsTest {

    @Test
    @DisplayName("카드 합을 계산한다.")
    void sum_hands() {
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
}
