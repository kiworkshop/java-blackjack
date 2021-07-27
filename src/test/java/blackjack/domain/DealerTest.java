package blackjack.domain;

import blackjack.enums.Denomination;
import blackjack.enums.Type;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class DealerTest {

    @DisplayName("처음에 받은 두 장의 카드 합이 16 이하이면 카드를 한 장 받는다.")
    @Test
    void drawCardTest() {
        //given
        Dealer dealer = new Dealer();
        Deck deck = new Deck();
        dealer.receiveCard((new Card(Denomination.EIGHT, Type.CLUB)));
        dealer.receiveCard((new Card(Denomination.EIGHT, Type.HEART)));

        //when
        dealer.drawOrNot(deck);

        //then
        assertThat(dealer.getCards().size()).isEqualTo(3);
    }

    @DisplayName("처음에 받은 두 장의 카드 합이 16 초과이면 카드를 더 받지 않는다.")
    @Test
    void NotDrawCardTest() {
        //given
        Dealer dealer = new Dealer();
        Deck deck = new Deck();
        dealer.receiveCard(new Card(Denomination.NINE, Type.CLUB));
        dealer.receiveCard((new Card(Denomination.EIGHT, Type.HEART)));

        //when
        dealer.drawOrNot(deck);

        //then
        assertThat(dealer.getCards().size()).isEqualTo(2);
    }
}