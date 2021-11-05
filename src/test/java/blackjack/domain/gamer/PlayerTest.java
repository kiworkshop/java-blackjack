package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.game.Hands;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static blackjack.domain.card.Deck.INITIAL_DEAL_COUNT;
import static blackjack.domain.card.TestCard.*;
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
        assertThat(player.name()).isEqualTo(name);
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
        player.hit(SPADE_8);

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
        hitPlayer.hit(SPADE_8);

        //then
        assertThat(neverHitPlayer.neverHit()).isTrue();
        assertThat(hitPlayer.neverHit()).isFalse();
    }

    @Test
    @DisplayName("블랙잭인 것을 확인한다.")
    void blackjack() {
        // given
        Player blackjackPlayer = new Player("blackjack", new Hands(Arrays.asList(SPADE_A, SPADE_Q)));
        Player notBlackjackPlayer = new Player("notBlackjack", new Hands(Arrays.asList(SPADE_A, SPADE_9)));

        // when
        boolean blackjack = blackjackPlayer.isBlackjack();
        boolean notBlackjack = notBlackjackPlayer.isBlackjack();

        // then
        assertThat(blackjack).isTrue();
        assertThat(notBlackjack).isFalse();
    }

    @Test
    @DisplayName("버스트인 것을 확인한다.")
    void bust() {
        // given
        Player bustPlayer = new Player("bust", new Hands(Arrays.asList(SPADE_3, SPADE_K, SPADE_Q)));
        Player notBustPlayer = new Player("notBust", new Hands(Arrays.asList(SPADE_A, SPADE_9)));

        // when
        boolean bust = bustPlayer.isBust();
        boolean notBust = notBustPlayer.isBust();

        // then
        assertThat(bust).isTrue();
        assertThat(notBust).isFalse();
    }

    private Hands generateHands() {
        List<Card> cards = new ArrayList<>();
        cards.add(SPADE_8);
        cards.add(SPADE_9);
        return new Hands(cards);
    }
}
