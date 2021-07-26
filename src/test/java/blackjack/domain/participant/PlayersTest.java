package blackjack.domain.participant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayersTest {

    @Test
    @DisplayName("여러 플레이어를 생성한다.")
    void create() {
        //given
        List<String> userInput = Arrays.asList("pobi", "jason");

        //when
        Players players = new Players(userInput);

        //then
        assertThat(players.size()).isEqualTo(2);
    }
}
