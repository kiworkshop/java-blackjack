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

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class TotalResultTest {

    private static Stream<Arguments> createResult() {
        return Stream.of(
                Arguments.of(WinningResult.WIN, 2),
                Arguments.of(WinningResult.TIE, 1),
                Arguments.of(WinningResult.LOSE, 0)
        );
    }


    @ParameterizedTest
    @EnumSource(WinningResult.class)
    @DisplayName("각 플레이어의 승패를 확인한다.")
    void playerResultTest(WinningResult winningResult) {
        //given
        Dealer dealer = new Dealer();
        List<Player> players = PlayersFactory.createPlayers("Chris, Matilda, Jenny");

        dealer.receiveCard(new Card(Denomination.NINE, Type.CLUB));

        players.get(0)
                .receiveCard((new Card(Denomination.FOUR, Type.DIAMOND)));  // 패

        players.get(1)
                .receiveCard((new Card(Denomination.NINE, Type.HEART)));    // 무승부

        players.get(2)
                .receiveCard((new Card(Denomination.QUEEN, Type.SPADE)));   // 승

        //when
        TotalResult totalResult = TotalResult.of(dealer, players);

        //then
        assertThat(totalResult.getPlayersResult()).containsValues(winningResult);

    }

}