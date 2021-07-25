package blackjack.card;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeckTest {

    @Test
    @DisplayName("무늬별로 숫자, 메이저, 에이스 카드를 총 52장의 카드를 갖는다.")
    void generator() {
        //given, when
        Deck deck = new Deck();

        //then
        Assertions.assertThat(deck.size()).isEqualTo(52);
    }
}
