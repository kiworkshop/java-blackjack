package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Type;
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

    @DisplayName("딜러가 갖고 있는 카드를 출력한다.")
    @Test
    void DealerPrintTest() {
        //given
        Dealer dealer = new Dealer();
        Deck deck = new Deck();
        dealer.receiveCard(new Card(Denomination.NINE, Type.CLUB));
        dealer.receiveCard((new Card(Denomination.EIGHT, Type.HEART)));

        //when

        //then
        assertThat(dealer.toString()).isEqualTo("딜러: 9클로버, 8하트");
    }
}