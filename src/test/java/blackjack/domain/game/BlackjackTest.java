package blackjack.domain.game;

import blackjack.domain.participant.Player;
import blackjack.dto.PlayerDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static blackjack.domain.game.Deck.INITIAL_DEAL_COUNT;
import static org.assertj.core.api.Assertions.assertThat;

class BlackjackTest {
    @Test
    @DisplayName("플레이어에게 초기 카드를 배분한다.")
    void initial_deal_for_players() {
        // given
        List<String> playerNames = generatePlayerNames();

        // when
        Blackjack blackjack = new Blackjack(playerNames);

        // then
        List<Player> players = blackjack.getPlayers();
        players.forEach(player -> assertThat(player.countHands()).isEqualTo(INITIAL_DEAL_COUNT));
    }

    @Test
    @DisplayName("플레이어에게 추가 카드 배분을 진행한다.")
    void hit_or_stand() {
        //given
        List<String> playerNames = generatePlayerNames();
        Blackjack blackjack = new Blackjack(playerNames);
        List<Player> players = blackjack.getPlayers();

        //when
        PlayerDto playerDto = blackjack.hit(players.get(0));

        //then
        assertThat(playerDto.getCards().size()).isEqualTo(3);
    }

    private List<String> generatePlayerNames() {
        List<String> playerNames = new ArrayList<>();
        playerNames.add("pobi");
        playerNames.add("jason");
        return playerNames;
    }
}
