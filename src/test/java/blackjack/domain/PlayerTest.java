package blackjack.domain;

import blackjack.enums.Denomination;
import blackjack.enums.Type;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayerTest {

    @DisplayName("이름을 입력받아 플레이어 객체를 생성한다.")
    @Test
    void createPlayer() {
        //given
        String name = "John Doe";
        Player player = new Player(name);

        //when //then
        assertThat(player.getName()).isEqualTo("John Doe");
    }

    @DisplayName("플레이어가 카드를 지급받아 갖고 있는지 확인")
    @Test
    void receiveCard() {
        //given
        Player player = new Player("John Doe");
        Card card1 = new Card(Denomination.EIGHT, Type.DIAMOND);
        Card card2 = new Card(Denomination.FOUR, Type.SPADE);

        //when
        player.receiveCard(card1);
        player.receiveCard(card2);

        //then
        assertThat(player.getCards()).contains(card1);
        assertThat(player.getCards()).contains(card2);
    }
}
