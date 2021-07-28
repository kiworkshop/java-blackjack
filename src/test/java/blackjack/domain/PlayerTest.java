package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {

    @Test
    @DisplayName("사람이름을 입력 받아서 입력받은 플레이어 객체를 만든다")
    void create() {
        //given
        String name = "홍길동";

        //when
        Player player = new Player(name);

        //then
        assertThat(player.getName()).isEqualTo(name);
        assertThat(player.getCards()).hasSize(0);
    }

    @Test
    @DisplayName("카드를 추가로 한장 더 발급 받는다.")
    void addCard() {
        //given
        CardDeck cardDeck = new CardDeck();
        Player player = new Player("pobi");

        //when
        player.addCard(cardDeck.getAdditionalCard());

        //then
        assertThat(player.getCards()).hasSize(1);
    }
}
