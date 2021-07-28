package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CardDeckTest {
    @Test
    @DisplayName("카드 덱을 생성하면 52장의 카드가 생긴다")
    void create() {
        //given
        CardDeck cardDeck = new CardDeck();

        //when
        int size = cardDeck.getSize();

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

    @Test
    @DisplayName("카드가 없을때 카드를 뽑으면 예외가 발생한다")
    void getAdditionlCard_throw_exception_no_cards_left() {
        //given
        CardDeck cardDeck = new CardDeck();
        IntStream.range(0, 52).forEach(i -> cardDeck.getAdditionalCard());

        //when //then
        assertThatThrownBy(cardDeck::getAdditionalCard)
                .isInstanceOf(IndexOutOfBoundsException.class)
                .hasMessage("더이상 카드가 없습니다.");
    }
}
