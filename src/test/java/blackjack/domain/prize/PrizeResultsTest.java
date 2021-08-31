package blackjack.domain.prize;

import blackjack.domain.game.Hands;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static blackjack.domain.card.TestCard.*;
import static org.assertj.core.api.Assertions.assertThat;

class PrizeResultsTest {

    @ParameterizedTest
    @MethodSource("generateCompareHands")
    @DisplayName("딜러와 플레이어의 카드 합을 비교한다.")
    void compare_rank_sum(Dealer dealer, List<Player> players,
                          int winCount, int loseCount, int tieCount,
                          List<String> playersResults) {
        //given, when
        PrizeResults prizeResults = new PrizeResults(dealer, players);
        DealerPrize dealerPrize = prizeResults.dealerPrize();
        PlayersPrize playersPrize = prizeResults.playersPrize();

        //then
        assertThat(dealerPrize.getWinCount()).isEqualTo(winCount);
        assertThat(dealerPrize.getLoseCount()).isEqualTo(loseCount);
        assertThat(dealerPrize.getTieCount()).isEqualTo(tieCount);

        for (int i = 0; i < players.size(); i++) {
            PlayerPrize playerPrize = playersPrize.values().get(i);
            assertThat(playerPrize.value().getTitle()).isEqualTo(playersResults.get(i));
        }
    }

    private static List<Player> generatePlayers() {
        return Arrays.asList(
                new Player("1", new Hands(Arrays.asList(SPADE_2, SPADE_8, SPADE_6))),   // 16
                new Player("2", new Hands(Arrays.asList(SPADE_K, SPADE_7))),            // 17
                new Player("3", new Hands(Arrays.asList(SPADE_3, SPADE_Q, SPADE_6))),   // 19
                new Player("4", new Hands(Arrays.asList(SPADE_3, SPADE_Q, SPADE_6))),   // 19
                new Player("블랙잭", new Hands(Arrays.asList(SPADE_Q, SPADE_A))),         // blackjack
                new Player("5", new Hands(Arrays.asList(SPADE_Q, SPADE_8, SPADE_9)))    // 27
        );
    }

    private static Dealer generateDealer() { // 19
        return new Dealer(new Hands(Arrays.asList(SPADE_9, SPADE_K)));
    }

    private static Dealer generateWinDealer() { // 21
        return new Dealer(new Hands(Arrays.asList(SPADE_6, SPADE_7, SPADE_8)));
    }

    private static Dealer generateBlackjackDealer() { // 21
        return new Dealer(new Hands(Arrays.asList(SPADE_A, SPADE_Q)));
    }

    private static Dealer generateBustDealer() { // 23
        return new Dealer(new Hands(Arrays.asList(SPADE_6, SPADE_7, SPADE_Q)));
    }

    private static Stream<Arguments> generateCompareHands() {
        return Stream.of(
                Arguments.of(generateDealer(), generatePlayers(), 3, 1, 2, Arrays.asList("패", "패", "무", "무", "승", "패")),
                Arguments.of(generateWinDealer(), generatePlayers(), 5, 1, 0, Arrays.asList("패", "패", "패", "패", "승", "패")),
                Arguments.of(generateBlackjackDealer(), generatePlayers(), 6, 0, 0, Arrays.asList("패", "패", "패", "패", "승", "패")),
                Arguments.of(generateBustDealer(), generatePlayers(), 0, 6, 0, Arrays.asList("승", "승", "승", "승", "승", "패"))
        );
    }
}
