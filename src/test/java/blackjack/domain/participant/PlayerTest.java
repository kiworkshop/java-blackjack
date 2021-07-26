package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {

    @Test
    @DisplayName("플레이어 객체를 생성한다.")
    void create() {
        //given
        String name = "pobi";

        //when
        Player player = new Player(name);

        //then
        assertThat(player.getName()).isEqualTo(name);
    }

    @Test
    @DisplayName("한 장의 카드를 추가한다.")
    void hit() {
        //given
        String name = "pobi";

        //when
        Player player = new Player(name);
        player.hit(new Card(Suit.CLUB, 8));

        //then
        assertThat(player.countHands()).isEqualTo(1);
    }
}
