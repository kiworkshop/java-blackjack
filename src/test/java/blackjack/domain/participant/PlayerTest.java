package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.game.Hands;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static blackjack.domain.card.TestCard.*;
import static blackjack.domain.game.Deck.INITIAL_DEAL_COUNT;
import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {

    @Test
    @DisplayName("플레이어 객체를 생성한다.")
    void create() {
        //given
        String name = "pobi";
        Hands hands = generateHands();

        //when
        Player player = new Player(name, hands);

        //then
        assertThat(player.getName()).isEqualTo(name);
        assertThat(player.countHands()).isEqualTo(INITIAL_DEAL_COUNT);
    }

    @Test
    @DisplayName("한 장의 카드를 추가한다.")
    void hit() {
        //given
        String name = "pobi";
        Hands hands = generateHands();

        //when
        Player player = new Player(name, hands);
        player.take(CARD_8);

        //then
        assertThat(player.countHands()).isEqualTo(3);
    }

    @Test
    @DisplayName("카드를 추가로 받지 않았는지 확인한다.")
    void never_hit() {
        //given
        Player neverHitPlayer = new Player("name", generateHands());
        Player hitPlayer = new Player("name", generateHands());

        //when
        hitPlayer.take(CARD_8);

        //then
        assertThat(neverHitPlayer.neverHit()).isTrue();
        assertThat(hitPlayer.neverHit()).isFalse();
    }

    @Test
    @DisplayName("블랙잭인 것을 확인한다.")
    void blackjack() {
        // given
        Player blackjackPlayer = new Player("blackjack", new Hands(Arrays.asList(ACE_1, CARD_Q)));
        Player notBlackjackPlayer = new Player("notBlackjack", new Hands(Arrays.asList(ACE_1, CARD_9)));

        // when
        boolean blackjack = blackjackPlayer.blackjack();
        boolean notBlackjack = notBlackjackPlayer.blackjack();

        // then
        assertThat(blackjack).isTrue();
        assertThat(notBlackjack).isFalse();
    }

    @Test
    @DisplayName("버스트인 것을 확인한다.")
    void bust() {
        // given
        Player bustPlayer = new Player("bust", new Hands(Arrays.asList(CARD_3, CARD_K, CARD_Q)));
        Player notBustPlayer = new Player("notBust", new Hands(Arrays.asList(ACE_1, CARD_9)));

        // when
        boolean bust = bustPlayer.bust();
        boolean notBust = notBustPlayer.bust();

        // then
        assertThat(bust).isTrue();
        assertThat(notBust).isFalse();
    }

    private Hands generateHands() {
        List<Card> cards = new ArrayList<>();
        cards.add(CARD_8);
        cards.add(CARD_9);
        return new Hands(cards);
    }
}
