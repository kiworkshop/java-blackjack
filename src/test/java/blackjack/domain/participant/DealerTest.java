package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.game.Hands;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static blackjack.domain.card.Deck.INITIAL_DEAL_COUNT;
import static blackjack.domain.card.TestCard.*;
import static org.assertj.core.api.Assertions.assertThat;

class DealerTest {

    @Test
    @DisplayName("딜러 객체를 생성한다.")
    void create() {
        //given
        Hands hands = generateHitHands();

        //when
        Dealer dealer = new Dealer(hands);

        //then
        assertThat(dealer.countHands()).isEqualTo(INITIAL_DEAL_COUNT);
    }

    @Test
    @DisplayName("오픈할 카드만 반환한다.")
    void get_face_up_card() {
        //given
        Hands hands = generateHitHands();

        // when
        Dealer dealer = new Dealer(hands);
        Card faceUpCard = dealer.getFaceUpCard();

        //then
        assertThat(faceUpCard.rank()).isEqualTo(10);
    }

    @Test
    @DisplayName("카드 합이 16 이하인 경우 한 장의 카드를 추가한다.")
    void final_deal_on_hit() {
        //given
        Hands hands = generateHitHands();

        //when
        Dealer dealer = new Dealer(hands);

        //then
        assertThat(dealer.hit()).isTrue();
    }

    @Test
    @DisplayName("카드 합이 16 초과인 경우 카드를 추가하지 않는다.")
    void final_deal_on_stand() {
        //given
        Hands hands = generateStandHands();

        //when
        Dealer dealer = new Dealer(hands);

        //then
        assertThat(dealer.hit()).isFalse();
    }

    @Test
    @DisplayName("카드를 추가한다.")
    void take_card() {
        //given
        Hands hands = generateStandHands();
        Dealer dealer = new Dealer(hands);

        //when
        dealer.draw(SPADE_3);

        // then
        assertThat(dealer.countHands()).isEqualTo(3);
    }

    private Hands generateHitHands() {
        List<Card> cards = new ArrayList<>();
        cards.add(SPADE_Q);
        cards.add(SPADE_6);
        return new Hands(cards);
    }

    private Hands generateStandHands() {
        List<Card> cards = new ArrayList<>();
        cards.add(SPADE_J);
        cards.add(SPADE_K);
        return new Hands(cards);
    }
}
