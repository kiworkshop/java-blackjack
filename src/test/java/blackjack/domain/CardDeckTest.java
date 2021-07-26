package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CardDeckTest {
    @Test
    @DisplayName("카드 덱을 생성하면 52장의 카드가 생긴다")
    void create() {
        //given
        CardDeck cardDeck = new CardDeck();

        //when
        int size = cardDeck.size();

        //then
        assertThat(size).isEqualTo(52);
    }
}
