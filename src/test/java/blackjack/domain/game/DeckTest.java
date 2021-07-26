package blackjack.domain.game;

import blackjack.domain.card.Card;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static blackjack.domain.game.Deck.INITIAL_DEAL_COUNT;
import static blackjack.domain.game.Deck.TOTAL_CARD_COUNT;
import static org.assertj.core.api.Assertions.assertThat;

class DeckTest {

    @Test
    @DisplayName("무늬별로 숫자, 메이저, 에이스 카드를 총 52장의 카드를 갖는다.")
    void generator() {
        //given, when
        Deck deck = new Deck();

        //then
        assertThat(deck.size()).isEqualTo(TOTAL_CARD_COUNT);
    }

    @Test
    @DisplayName("중복되지 않는 카드를 한 장 반환한다.")
    void random_card() {
        //given
        Deck deck = new Deck();

        //when
        Card card = deck.draw();

        //then
        assertThat(deck.size()).isEqualTo(51);
    }

    @Test
    @DisplayName("초기 딜을 위한 중복되지 않는 카드 2장을 반환한다.")
    void initial_deal() {
        // given
        Deck deck = new Deck();

        // when
        Hands hands = deck.drawInitialHands();

        // then
        assertThat(hands.size()).isEqualTo(INITIAL_DEAL_COUNT);
    }
}
