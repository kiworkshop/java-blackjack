package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DeckTest {

    @Test
    @DisplayName("카드 한 장을 반환한다.")
    void getCard() {
        //when
        Card card = Deck.getCard();

        //then
        assertThat(card).isNotNull();
    }

    @Test
    @DisplayName("카드 두 장을 반환한다.")
    void getTwoCards() {
        //when
        GivenCards cards = Deck.getTwoCards();

        //then
        assertThat(cards.list()).hasSize(2);
    }
}
