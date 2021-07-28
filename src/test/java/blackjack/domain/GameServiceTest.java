package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class GameServiceTest {

    @Test
    @DisplayName("참가자 이름 리스트로 등록하면 게임에 참가자들이 생성된다")
    void create() {
        //given
        GameService gameService = new GameService(Arrays.asList("pobi", "jason"));

        //when
        List<Player> players = gameService.getPlayers();

        //then
        assertThat(players).hasSize(2)
                .extracting("name")
                .contains("pobi", "jason");
        assertThat(players.get(0).getName()).isEqualTo("pobi");
        assertThat(players.get(1).getName()).isEqualTo("jason");
    }
}
