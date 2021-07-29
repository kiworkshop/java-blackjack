package blackjack.domain.game;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.domain.prize.DealerPrize;
import blackjack.domain.prize.PlayerPrize;
import blackjack.domain.prize.PlayersPrize;
import blackjack.domain.prize.PrizeResults;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static blackjack.domain.card.TestCard.*;
import static org.assertj.core.api.Assertions.assertThat;

public class PrizeResultsTest {


    @ParameterizedTest
    @MethodSource("generateCompareHands")
    @DisplayName("딜러와 플레이어의 카드 합을 비교한다.")
    void compare_rank_sum(Dealer dealer, Players players,
                          int winCount, int loseCount, int tieCount, List<String> playersResults) {
        //given, when
        PrizeResults prizeResults = new PrizeResults(dealer, players);
        DealerPrize dealerPrize = prizeResults.getDealerPrize();
        PlayersPrize playersPrize = prizeResults.getPlayersPrize();

        //then
        assertThat(dealerPrize.getWinCount()).isEqualTo(winCount);
        assertThat(dealerPrize.getLoseCount()).isEqualTo(loseCount);
        assertThat(dealerPrize.getTieCount()).isEqualTo(tieCount);

        for (int i = 0; i < players.size(); i++) {
            PlayerPrize playerPrize = playersPrize.getPlayerPrizes().get(i);
            assertThat(playerPrize.getPrize().getTitle()).isEqualTo(playersResults.get(i));
        }
    }

    private static Players generatePlayers() {
        List<Player> players = Arrays.asList(
                new Player("패자", new Hands(Arrays.asList(CARD_2, CARD_8, CARD_6))), // 16
                new Player("패자", new Hands(Arrays.asList(CARD_K, CARD_7))), // 17
                new Player("동점자", new Hands(Arrays.asList(CARD_3, CARD_Q, CARD_6))), // 19
                new Player("동점자", new Hands(Arrays.asList(CARD_3, CARD_Q, CARD_6))), // 19
                new Player("블랙잭", new Hands(Arrays.asList(CARD_Q, ACE_1))), // blackjack
                new Player("패자", new Hands(Arrays.asList(CARD_Q, CARD_8, CARD_9))) // 27
        );
        return new Players(players);
    }

    private static Dealer generateDealer() { // 19
        return new Dealer(new Hands(Arrays.asList(CARD_9, CARD_K)));
    }

    private static Dealer generateWinDealer() { // 21
        return new Dealer(new Hands(Arrays.asList(CARD_6, CARD_7, CARD_8)));
    }

    private static Dealer generateBlackjackDealer() { // 21
        return new Dealer(new Hands(Arrays.asList(ACE_3, CARD_Q)));
    }

    private static Dealer generateBustDealer() { // 23
        return new Dealer(new Hands(Arrays.asList(CARD_6, CARD_7, CARD_Q)));
    }

    private static Stream<Arguments> generateCompareHands() {
        return Stream.of(
                Arguments.of(generateDealer(), generatePlayers(), 3, 1, 2, Arrays.asList("패", "패", "무", "무", "승", "패"))
//                Arguments.of(generateDealer(), generatePlayers(), 3, 2, 1, Arrays.asList("패", "패", "무", "무", "승", "패")),
//                Arguments.of(generateDealer(), generatePlayers(), 3, 2, 1, Arrays.asList("패", "패", "무", "무", "승", "패")),
//                Arguments.of(generateDealer(), generatePlayers(), 3, 2, 1, Arrays.asList("패", "패", "무", "무", "승", "패")),
//                Arguments.of(generateDealer(), generatePlayers(), 3, 2, 1, Arrays.asList("패", "패", "무", "무", "승", "패")),
//                Arguments.of(generateDealer(), generatePlayers(), 3, 2, 1, Arrays.asList("패", "패", "무", "무", "승", "패")),
//                Arguments.of(generateDealer(), generatePlayers(), 3, 2, 1, Arrays.asList("패", "패", "무", "무", "승", "패"));

        );
    }
}
