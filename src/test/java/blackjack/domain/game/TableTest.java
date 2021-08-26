package blackjack.domain.game;

import blackjack.domain.participant.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static blackjack.domain.card.Deck.INITIAL_DEAL_COUNT;
import static org.assertj.core.api.Assertions.assertThat;

class TableTest {
    @Test
    @DisplayName("딜러와 플레이어에게 초기 카드 배분한다.")
    void create_table() {
        // given
        List<String> playerNames = generatePlayerNames();

        // when
        Table table = new Table(playerNames);
        List<Player> players = table.players();

        // then
        assertThat(table.deck().size()).isEqualTo(46);
        assertThat(table.dealer().countHands()).isEqualTo(INITIAL_DEAL_COUNT);
        players.forEach(player -> assertThat(player.countHands()).isEqualTo(INITIAL_DEAL_COUNT));
    }

    @Test
    @DisplayName("플레이어에게 추가 카드를 배분한다.")
    void hit_or_stand() {
        //given
        List<String> playerNames = generatePlayerNames();
        Table table = new Table(playerNames);
        List<Player> players = table.players();

        //when
        Player player = table.hit(players.get(0));

        //then
        assertThat(player.cards().size()).isEqualTo(3);
        assertThat(table.deck().size()).isEqualTo(45);
    }

    private List<String> generatePlayerNames() {
        List<String> playerNames = new ArrayList<>();
        playerNames.add("pobi");
        playerNames.add("jason");
        return playerNames;
    }
}
