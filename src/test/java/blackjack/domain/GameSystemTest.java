package blackjack.domain;

import blackjack.domain.card.GivenCards;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class GameSystemTest {

    @Test
    @DisplayName("플레이어의 이름을 반환한다.")
    void getPlayerNames() {
        //given
        String name1 = "pobi";
        String name2 = "tobi";
        List<String> names = Arrays.asList(name1, name2);
        GivenCards givenCards = new GivenCards(Collections.emptyList());

        //when
        GameSystem gameSystem = new GameSystem(names);

        //then
        assertThat(gameSystem.getPlayerNames()).hasSize(2)
                .contains(name1, name2);
    }
}
