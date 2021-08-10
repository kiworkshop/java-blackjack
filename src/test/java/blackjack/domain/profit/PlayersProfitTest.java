package blackjack.domain.profit;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.prize.PlayersPrize;
import blackjack.domain.table.BettingTable;
import blackjack.dto.PlayerInput;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static blackjack.domain.fixture.TestCards.*;
import static org.assertj.core.api.Assertions.assertThat;

class PlayersProfitTest {
    @Test
    @DisplayName("베팅테이블과 플레이어들의 승패결과가 주어지면 플레이어들의 이익을 반환한다.")
    void generate() {
        // given
        BettingTable bettingTable = generateBettingTable();
        PlayersPrize playersPrize = generatePlayersPrize();

        // when
        PlayersProfit playersProfit = new PlayersProfit(bettingTable, playersPrize);
        List<PlayerProfit> profits = playersProfit.getPlayerPrizes();

        // then
        assertThat(profits).hasSize(3);
        assertThat(profits.get(0).getPlayerName()).isEqualTo("a");
        assertThat(profits.get(1).getPlayerName()).isEqualTo("b");
        assertThat(profits.get(2).getPlayerName()).isEqualTo("c");
    }

    @Test
    @DisplayName("플레이어들이 받는 상금의 총 합 금액을 반환한다.")
    void calculate_total_amount() {
        // given
        BettingTable bettingTable = generateBettingTable();
        PlayersPrize playersPrize = generatePlayersPrize();
        PlayersProfit playersProfit = new PlayersProfit(bettingTable, playersPrize);

        // when
        int totalAmount = playersProfit.calculateTotalAmount();

        // then
        assertThat(totalAmount).isEqualTo(200 + 500);
    }

    private PlayersPrize generatePlayersPrize() {
        List<Player> players = Arrays.asList(
                new Player("a", winCards()),
                new Player("b", blackjackCards()),
                new Player("c", holdCardsForLose())
        );
        Dealer dealer = new Dealer(holdCardsForWin());
        return new PlayersPrize(players, dealer);
    }

    private BettingTable generateBettingTable() {
        PlayerInput a = new PlayerInput("a", 100);
        PlayerInput b = new PlayerInput("b", 200);
        PlayerInput c = new PlayerInput("c", 300);
        List<PlayerInput> playerInputs = Arrays.asList(a, b, c);
        return new BettingTable(playerInputs);
    }
}