package blackjack.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GameBettingResult {

    List<GamePlayerResult> playerResults;
    private double dealerEarnMoney;

    public GameBettingResult(List<GamePlayerResult> playerResults) {
        this.playerResults = playerResults;
        playerResults.stream().forEach(gamePlayerResult -> dealerEarnMoney = dealerEarnMoney + gamePlayerResult.getEarnMoney());
        dealerEarnMoney = dealerEarnMoney * -1;
    }

    public List<GamePlayerResult> getPlayerResults() {
        return playerResults;
    }

    public double getDealerEarnMoney() {
        return dealerEarnMoney;
    }

}
