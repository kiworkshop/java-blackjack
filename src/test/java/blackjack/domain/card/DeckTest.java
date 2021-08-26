package blackjack.domain.card;

import blackjack.domain.game.Hands;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static blackjack.domain.card.Deck.INITIAL_DEAL_COUNT;
import static org.assertj.core.api.Assertions.assertThat;

class DeckTest {
    private Deck deck;

    @BeforeEach
    void before() {
        deck = Deck.of(DeckInitializer.init());
    }

    @Test
    @DisplayName("무늬별로 숫자, 메이저, 에이스 카드를 총 52장의 카드를 갖는다.")
    void generator() {
        //then
        assertThat(deck.size()).isEqualTo(52);
    }

    @Test
    @DisplayName("중복되지 않는 카드를 한 장 반환한다.")
    void random_card() {
        //when
        deck.draw();

        //then
        assertThat(deck.size()).isEqualTo(51);
    }

    @Test
    @DisplayName("초기 딜을 위한 중복되지 않는 카드 2장을 반환한다.")
    void initial_deal() {
        // when
        Hands hands = deck.drawInitialHands();

        // then
        assertThat(hands.size()).isEqualTo(INITIAL_DEAL_COUNT);
    }
}
