package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.game.Hands;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static blackjack.domain.card.TestCard.CARD_8;
import static blackjack.domain.card.TestCard.CARD_9;
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

    private Hands generateHands() {
        List<Card> cards = new ArrayList<>();
        cards.add(CARD_8);
        cards.add(CARD_9);
        return new Hands(cards);
    }
}
