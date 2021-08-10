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

class StayTest {

    @Test
    @DisplayName("hit하면 런타임에러를 리턴한다")
    void hit() {
        //given
        State state = new Stay();
        List<Card> cards = Arrays.asList(new Card(Denomination.Q, Suit.CLUB), new Card(Denomination.FIVE, Suit.CLUB), new Card(Denomination.TEN, Suit.CLUB));

        //when //then
        assertThatThrownBy(() -> state.hit(new Cards(cards))).isInstanceOf(RuntimeException.class);

    }

    @Test
    @DisplayName("stay하면 런타임에러를 리턴한다")
    void stay() {
        //given
        State state = new Stay();

        //when //then
        assertThatThrownBy(() -> state.stay()).isInstanceOf(RuntimeException.class);

    }

    @Test
    @DisplayName("가지고있는 카드 점수를 리턴한다")
    void getScore() {
        //given
        State state = new Stay();
        List<Card> cards = Arrays.asList(new Card(Denomination.NINE, Suit.CLUB), new Card(Denomination.TEN, Suit.CLUB));

        //when
        int result = state.getScore(new Cards(cards));

        //then
        assertThat(result).isEqualTo(19);

    }
}