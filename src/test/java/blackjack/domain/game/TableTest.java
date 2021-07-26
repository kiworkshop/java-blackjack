package blackjack.domain.game;

import blackjack.domain.participant.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static blackjack.domain.game.Deck.INITIAL_DEAL_COUNT;
import static org.assertj.core.api.Assertions.assertThat;

class TableTest {

    @Test
    @DisplayName("테이블을 생성한다.")
    void create_table() {
        // given
        List<String> playerNames = generatePlayerNames();

        // when
        Table table = new Table(playerNames);

        // then
        assertThat(table.getCardSize()).isEqualTo(46);
        assertThat(table.getPlayers().size()).isEqualTo(INITIAL_DEAL_COUNT);
    }

    @Test
    @DisplayName("플레이어에게 초기 카드를 배분한다.")
    void initial_deal_for_players() {
        // given
        List<String> playerNames = generatePlayerNames();
        Table table = new Table(playerNames);

        // when
        List<Player> players = table.getPlayers();

        // then
        players.forEach(player -> assertThat(player.countHands()).isEqualTo(INITIAL_DEAL_COUNT));
    }

    private List<String> generatePlayerNames() {
        List<String> playerNames = new ArrayList<>();
        playerNames.add("pobi");
        playerNames.add("jason");
        return playerNames;
    }
}
