package blackjack.domain;

import blackjack.enums.Denomination;
import blackjack.enums.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class CardTest {

    @Test
    @DisplayName("카드의 점수를 리턴한다.")
    void getCardScore() {
        //given
        Cards cards = new Cards(Arrays.asList(new Card(Denomination.NINE, Suit.CLUB), new Card(Denomination.TEN, Suit.CLUB)));

        //when
        int score = cards.getCardScore();

        //then
        assertThat(score).isEqualTo(19);
    }

    @Test
    @DisplayName("카드의 합이 21을 초과하면 true, 아니면 false를 리턴한다")
    void isBust() {
        //given
        Cards cards = new Cards(Arrays.asList(new Card(Denomination.NINE, Suit.CLUB), new Card(Denomination.TEN, Suit.CLUB)));
        Cards cards2 = new Cards(Arrays.asList(new Card(Denomination.NINE, Suit.CLUB), new Card(Denomination.K, Suit.CLUB), new Card(Denomination.SEVEN, Suit.CLUB)));

        //when
        boolean result = cards.isBust();
        boolean result2 = cards2.isBust();

        //then
        assertThat(result).isFalse();
        assertThat(result2).isTrue();
    }

    @Test
    @DisplayName("카드의 합이 21이고 가진 카드가 2장이면 true, 아니면 false를 리턴한다")
    void isBlackJack() {
        //given
        Cards cards = new Cards(Arrays.asList(new Card(Denomination.A, Suit.CLUB), new Card(Denomination.TEN, Suit.CLUB)));
        Cards cards2 = new Cards(Arrays.asList(new Card(Denomination.NINE, Suit.CLUB), new Card(Denomination.K, Suit.CLUB), new Card(Denomination.SEVEN, Suit.CLUB)));

        //when
        boolean result = cards.isBlackJack();
        boolean result2 = cards2.isBlackJack();

        //then
        assertThat(result).isTrue();
        assertThat(result2).isFalse();
    }

    @Test
    @DisplayName("카드의 합이 21이고 가진 카드가 3장이면 true, 아니면 false를 리턴한다")
    void isStay() {
        //given
        Cards cards = new Cards(Arrays.asList(new Card(Denomination.A, Suit.CLUB), new Card(Denomination.TEN, Suit.CLUB)));
        Cards cards2 = new Cards(Arrays.asList(new Card(Denomination.TEN, Suit.CLUB), new Card(Denomination.FIVE, Suit.CLUB), new Card(Denomination.SIX, Suit.CLUB)));

        //when
        boolean result = cards.isStay();
        boolean result2 = cards2.isStay();

        //then
        assertThat(result).isFalse();
        assertThat(result2).isTrue();
    }
}