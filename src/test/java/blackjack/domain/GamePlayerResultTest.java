package blackjack.domain;

import blackjack.enums.Denomination;
import blackjack.enums.Suit;
import blackjack.service.GameService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GamePlayerResultTest {
    @Test
    @DisplayName("플레이어와 딜러의 점수를 비교해서 플레이어의 승패결과를 리턴한다")
    void getResult() {
        GameService gameService = new GameService();
        Player player = new Player("jason");
        player.hit(new Card(Denomination.EIGHT, Suit.CLUB));
        player.hit(new Card(Denomination.A, Suit.CLUB));
        player.stay();
        //when
        GamePlayerResult gamePlayerResult = new GamePlayerResult(player, 20);
        GamePlayerResult gamePlayerResult2 = new GamePlayerResult(player, 17);
        //then
        assertThat(gamePlayerResult.getResult()).isEqualTo("패");
        assertThat(gamePlayerResult2.getResult()).isEqualTo("승");
        assertThat(gamePlayerResult.getName()).isEqualTo("jason");
        assertThat(gamePlayerResult2.getName()).isEqualTo("jason");
    }
}
