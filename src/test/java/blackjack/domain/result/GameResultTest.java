package blackjack.domain.result;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Type;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.PlayersFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

class GameResultTest {

    @ParameterizedTest
    @EnumSource(WinningResult.class)
    @DisplayName("각 플레이어의 승패를 확인한다.")
    void playerResultTest(WinningResult winningResult) {
        //given
        Dealer dealer = new Dealer();
        List<Player> players = PlayersFactory.createPlayers("Chris, Matilda, Jenny");

        dealer.receiveCard(new Card(Denomination.NINE, Type.CLUB));

        players.get(0)
                .receiveCard((new Card(Denomination.FOUR, Type.DIAMOND)));  // 플레이어 패

        players.get(1)
                .receiveCard((new Card(Denomination.NINE, Type.HEART)));    // 플레이어 무승부

        players.get(2)
                .receiveCard((new Card(Denomination.QUEEN, Type.SPADE)));   // 플레이어 승

        //when
        GameResult gameResult = GameResult.of(dealer, players);

        //then
        assertThat(gameResult.getPlayersResult()).containsValues(winningResult);

    }

    @ParameterizedTest
    @MethodSource("dealerResultTest")
    @DisplayName("딜러의 승패를 확인한다.")
    void dealerResultTest(WinningResult winningResult, int result) {
        //given
        Dealer dealer = new Dealer();
        List<Player> players = PlayersFactory.createPlayers("Chris, Matilda, Jenny");

        dealer.receiveCard(new Card(Denomination.NINE, Type.CLUB));

        players.get(0)
                .receiveCard((new Card(Denomination.FOUR, Type.DIAMOND)));  // 플레이어 패 = 딜러 승1

        players.get(1)
                .receiveCard((new Card(Denomination.JACK, Type.HEART)));    // 플레이어 승 = 딜러 패1

        players.get(2)
                .receiveCard((new Card(Denomination.QUEEN, Type.SPADE)));   // 플레이어 승 = 딜러 패2

        //when
        GameResult gameResult = GameResult.of(dealer, players);

        //then
        assertThat(gameResult.getDealerResult()).contains(entry(winningResult, result));
    }

    private static Stream<Arguments> dealerResultTest() {
        return Stream.of(
                Arguments.of(WinningResult.WIN, 1),
                Arguments.of(WinningResult.LOSE, 2),
                Arguments.of(WinningResult.TIE, 0)
        );
    }

}
