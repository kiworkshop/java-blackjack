package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.game.Deck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DeckTest {

    @Test
    @DisplayName("무늬별로 숫자, 메이저, 에이스 카드를 총 52장의 카드를 갖는다.")
    void generator() {
        //given, when
        Deck deck = new Deck();

        //then
        assertThat(deck.size()).isEqualTo(52);
    }

    @Test
    @DisplayName("랜덤으로 카드를 한 장을 반환한다.")
    void random_card() {
        //given
        Deck deck = new Deck();

        //when
        Card card = deck.draw();

        //then
        assertThat(deck.size()).isEqualTo(51);
    }
}
