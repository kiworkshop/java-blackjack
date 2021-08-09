package blackjack.domain;

import blackjack.enums.Denomination;
import blackjack.enums.Suit;
import blackjack.service.GameService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class GameTotalResultTest {
    @Test
    @DisplayName("플레이어와 딜러의 점수를 비교해서 딜러의 승패 횟수를 리턴한다")
    void getResult() {
        GameService gameService = new GameService();
        Player player = new Player("jason");
        player.hit(new Card(Denomination.EIGHT, Suit.CLUB));
        player.hit(new Card(Denomination.TWO, Suit.CLUB));
        player.stay();
        Player player2 = new Player("pibo");
        player2.hit(new Card(Denomination.Q, Suit.CLUB));
        player2.hit(new Card(Denomination.A, Suit.CLUB));
        List<GamePlayerResult> gamePlayerResults = new ArrayList<>();
        gamePlayerResults.add(new GamePlayerResult(player, 17));
        gamePlayerResults.add(new GamePlayerResult(player2, 17));

        //when
        ;
        GameTotalReuslt gameTotalReuslt = new GameTotalReuslt(gamePlayerResults);
        //then
        assertThat(gameTotalReuslt.getDealerWinCount()).isEqualTo(1);
        assertThat(gameTotalReuslt.getDealerLoseCount()).isEqualTo(1);
        assertThat(gameTotalReuslt.getDealerTieCount()).isEqualTo(0);
    }
}
