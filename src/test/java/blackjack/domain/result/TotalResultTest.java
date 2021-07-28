package blackjack.domain.result;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.PlayersFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class TotalResultTest {

    private static Stream<Arguments> createResult() {
        return Stream.of(
                Arguments.of(WinningResult.WIN, 2),
                Arguments.of(WinningResult.LOSE, 1),
                Arguments.of(WinningResult.TIE, 0)
        );
    }
//    @Test
//    @DisplayName("")
//    void () {
//        //given
//        Dealer dealer = new Dealer();
//        List<Player> players = PlayersFactory.createPlayers("Chris, Matilda, Jenny");
//
//        //when
//
//        //then
//    }

}