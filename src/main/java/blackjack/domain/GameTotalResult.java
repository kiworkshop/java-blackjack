package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class GameTotalResult {
    List<GamePlayerResult> playerResults;
    private int dealerWinCount;
    private int dealerLoseCount;
    private int dealerTieCount;

    public GameTotalResult(List<GamePlayerResult> playerResults, Dealer dealer) {
        this.playerResults = playerResults;
        dealerWinCount = (int) playerResults.stream()
                .filter(gamePlayerResult -> gamePlayerResult.getResult().equals("패"))
                .count();
        dealerLoseCount = (int) playerResults.stream()
                .filter(gamePlayerResult -> gamePlayerResult.getResult().equals("승"))
                .count();
        dealerTieCount = (int) playerResults.stream()
                .filter(gamePlayerResult -> gamePlayerResult.getResult().equals("무"))
                .count();
    }

    public List<GamePlayerResult> getPlayerResults() {
        return playerResults;
    }

    public int getDealerWinCount() {
        return dealerWinCount;
    }

    public int getDealerLoseCount() {
        return dealerLoseCount;
    }

    public int getDealerTieCount() {
        return dealerTieCount;
    }
}
