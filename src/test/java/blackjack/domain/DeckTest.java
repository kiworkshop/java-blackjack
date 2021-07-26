package blackjack.domain;

import blackjack.enums.Denomination;
import blackjack.enums.Type;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DeckTest {

    @DisplayName("생성된 덱에 포함된 카드 숫자가 52장인지 확인한다")
    @Test
    void createTest() {
        // given
        Deck deck = new Deck();

        // when //then
        assertThat(deck.getDeck()).hasSize(52);
    }

    @DisplayName("생성된 덱에 스페이드 에이스가 포함되어 있다.")
    @Test
    void testDeckHasSpadeAce() {
        //given
        Deck deck = new Deck();

        //when //then
        assertTrue(deck.getDeck().contains(new Card(Denomination.ACE, Type.SPADE)));
    }

    @DisplayName("덱에서 플레이어에게 카드를 한 장 넘겨준다")
    @Test
    void giveCard() {
        //given
        Deck deck = new Deck();
        Player player = new Player("John Doe");

        //when
        player.receiveCard(deck.drawCard());

        //then
        assertThat(deck.getDeck()).hasSize(51);
        assertThat(player.getCards()).hasSize(1);
    }
}