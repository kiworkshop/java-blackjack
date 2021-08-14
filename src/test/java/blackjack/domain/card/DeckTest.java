package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DeckTest {

    @Test
    @DisplayName("카드 한 장을 반환한다.")
    void getCard() {
        //given
        Deck deck = new Deck();

        //when
        Card card = deck.getCard();

        //then
        assertThat(card).isNotNull();
    }

    @Test
    @DisplayName("카드 두 장을 반환한다.")
    void getTwoCards() {
        //given
        Deck deck = new Deck();

        //when
        GivenCards cards = deck.getTwoCards();

        //then
        assertThat(cards.list()).hasSize(2);
    }
}
