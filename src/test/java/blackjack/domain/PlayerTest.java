package blackjack.domain;

import blackjack.constant.Denomination;
import blackjack.constant.Type;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

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
}
