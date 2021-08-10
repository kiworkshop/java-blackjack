package blackjack.state;

import blackjack.domain.Card;
import blackjack.domain.Cards;
import blackjack.enums.Denomination;
import blackjack.enums.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class HitTest {

    @Test
    @DisplayName("가지고 있는 카드가 21 미만이면 hit상태를 리턴한다")
    void hitHit() {
        //given
        State state = new Hit();
        List<Card> cards = Arrays.asList(new Card(Denomination.TWO, Suit.CLUB));

        //when
        State result = state.hit(new Cards(cards));

        //then
        assertThat(result).isInstanceOf(Hit.class);

    }

    @Test
    @DisplayName("가지고 있는 카드가 21이고 a가 1장, 총 카드가 2장이면 blackjack상태를 리턴한다")
    void hitBlackJack() {
        //given
        State state = new Hit();
        List<Card> cards = Arrays.asList(new Card(Denomination.A, Suit.CLUB), new Card(Denomination.TEN, Suit.CLUB));

        //when
        State result = state.hit(new Cards(cards));

        //then
        assertThat(result).isInstanceOf(BlackJack.class);

    }

    @Test
    @DisplayName("가지고 있는 카드가 21을 초과하면 Bust 상태를 리턴한다")
    void hitBust() {
        //given
        State state = new Hit();
        List<Card> cards = Arrays.asList(new Card(Denomination.Q, Suit.CLUB), new Card(Denomination.FIVE, Suit.CLUB), new Card(Denomination.TEN, Suit.CLUB));

        //when
        State result = state.hit(new Cards(cards));

        //then
        assertThat(result).isInstanceOf(Bust.class);

    }

    @Test
    @DisplayName("가지고 있는 카드가 21이고 카드가 총 3장 이상이면 stay 상태를 리턴한다")
    void hitStay() {
        //given
        State state = new Hit();
        List<Card> cards = Arrays.asList(new Card(Denomination.SIX, Suit.CLUB), new Card(Denomination.FIVE, Suit.CLUB), new Card(Denomination.TEN, Suit.CLUB));

        //when
        State result = state.hit(new Cards(cards));

        //then
        assertThat(result).isInstanceOf(Stay.class);

    }

    @Test
    @DisplayName("stay를 리턴한다")
    void stay() {
        //given
        State state = new Hit();

        //when
        State result = state.stay();

        //then
        assertThat(result).isInstanceOf(Stay.class);
    }

    @Test
    @DisplayName("점수를 가지고 올 수 없다는 에러를 리턴한다.")
    void getScore() {
        //given
        State state = new Hit();
        List<Card> cards = Arrays.asList(new Card(Denomination.Q, Suit.CLUB), new Card(Denomination.FIVE, Suit.CLUB), new Card(Denomination.TEN, Suit.CLUB));

        //when //then
        assertThatThrownBy(() -> state.getScore(new Cards(cards))).isInstanceOf(RuntimeException.class);
    }
}