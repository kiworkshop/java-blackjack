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

    @Test
    @DisplayName("섞인카드 덱에서 2개의 카드를 순서대로 리턴한다.")
    void getFirstTwoCards() {
        //given
        CardDeck cardDeck = new CardDeck();

        //when
        List<Card> myCards = cardDeck.getFirstTwoCards();

        //then
        assertThat(myCards).hasSize(2);
        assertThat(cardDeck.getSize()).isEqualTo(50);
    }

    @Test
    @DisplayName("섞인 카드덱에서 1개의 카드를 반환한다")
    void getAdditionalCard() {
        //given
        CardDeck cardDeck = new CardDeck();

        //when
        Card additionalCard = cardDeck.getAdditionalCard();

        //then
        assertThat(additionalCard).isNotNull();
    }
}
