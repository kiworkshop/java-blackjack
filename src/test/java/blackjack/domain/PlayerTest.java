package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {
    @Test
    @DisplayName("플레이어이름, 베팅금액, 카드가 0개인 상태로 시작한다")
    void create() {
        //given
        Player player = new Player("jason", 1000);
        //when

        //then
        assertThat(player.getCards()).hasSize(0);
        assertThat(player.getName()).isEqualTo("jason");
        assertThat(player.getBettingMoney()).isEqualTo(1000);
    }
}
