package blackjack.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GameTotalReuslt {
    List<GamePlayerResult> playerResults = new ArrayList<>();
    int dealerWinCount;
    int dealerLoseCount;
    int dealerTieCount;

    public GameTotalReuslt(List<GamePlayerResult> playerResults) {
        this.playerResults = playerResults;
        dealerWinCount = (int) playerResults.stream()
                        .filter(gamePlayerResult -> gamePlayerResult.getResult().equals("승"))
                        .count();
        dealerLoseCount = (int) playerResults.stream()
                        .filter(gamePlayerResult -> gamePlayerResult.getResult().equals("패"))
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
