package blackjack.domain;

import blackjack.domain.card.Card;
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

        //when
        GameSystem gameSystem = new GameSystem(names);

        //then
        assertThat(gameSystem.getPlayerNames()).hasSize(2)
                .contains(name1, name2);
    }

    @Test
    @DisplayName("딜러의 모든 카드를 반환한다,")
    void getDealerCards() {
        //given
        GameSystem gameSystem = new GameSystem(Collections.emptyList());

        //when
        List<Card> dealerCards = gameSystem.getDealerCards();

        //then
        assertThat(dealerCards).hasSize(2);
    }

    @Test
    @DisplayName("플레이어들의 모든 카드를 반환한다.")
    void getPlayerCards() {
        //given
        String name1 = "pobi";
        String name2 = "tobi";
        List<String> names = Arrays.asList(name1, name2);
        GameSystem gameSystem = new GameSystem(names);

        //when
        List<List<Card>> playerCards = gameSystem.getPlayerCards();

        //then
        assertThat(playerCards).hasSize(2);
    }
}
