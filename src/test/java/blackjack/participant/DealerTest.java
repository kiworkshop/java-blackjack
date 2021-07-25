package blackjack.participant;

import blackjack.card.Card;
import blackjack.card.Hands;
import blackjack.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DealerTest {

    @Test
    @DisplayName("딜러 객체를 생성한다.")
    void create() {
        //given
        Hands hands = generateHands();

        //when
        Dealer dealer = new Dealer(hands);

        //then
        assertThat(dealer.countHands()).isEqualTo(2);
    }

    @Test
    @DisplayName("오픈할 카드만 반환한다.")
    void get_face_up_card() {
        //given
        Hands hands = generateHands();

        //when
        Dealer dealer = new Dealer(hands);
        Card faceUpCard = dealer.getFaceUpCard();

        //then
        assertThat(faceUpCard.getRank()).isEqualTo(1);
    }

    @Test
    @DisplayName("카드 합이 16 이하인 경우 한 장의 카드를 추가한다.")
    void final_deal_on_hit() {
        //given
        Hands hands = generateHands();

        //when
        Dealer dealer = new Dealer(hands);
        Hands finalHands = dealer.finalDeal(new Card(Suit.CLUB, 6));

        //then
        assertThat(finalHands.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("카드 합이 16 초과인 경우 카드를 추가하지 않는다.")
    void final_deal_on_stand() {
        //given
        Hands hands = generateStandHands();

        //when
        Dealer dealer = new Dealer(hands);
        Hands finalHands = dealer.finalDeal(new Card(Suit.CLUB, 6));

        //then
        assertThat(finalHands.size()).isEqualTo(2);
    }

    private Hands generateHands() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Suit.DIAMOND, 1));
        cards.add(new Card(Suit.DIAMOND, "K"));

        return new Hands(cards);
    }

    private Hands generateStandHands() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Suit.DIAMOND, "J"));
        cards.add(new Card(Suit.DIAMOND, "K"));

        return new Hands(cards);
    }
}
