package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CardTest {

    @DisplayName("카드 객체의 숫자와 종류값이 같으면 같은 객체로 판단한다")
    @Test
    void equalsTest() {
        //given, when, then
        assertThat(new Card(Denomination.TWO, Type.DIAMOND))
                .isEqualTo(new Card(Denomination.TWO, Type.DIAMOND));

    }

    @DisplayName("카드 객체의 숫자와 종류를 출력한다")
    @Test
    void printCardTwoDiamond() {
        //given
        Card card = new Card(Denomination.TWO, Type.DIAMOND);

        //when, then
        assertThat(card.toString()).isEqualTo("2다이아몬드");
    }

    @DisplayName("카드 객체의 숫자와 종류를 출력한다")
    @Test
    void printCardAceHeart() {
        //given
        Card card = new Card(Denomination.ACE, Type.HEART);

        //when, then
        assertThat(card.toString()).isEqualTo("A하트");
    }
}
