package blackjack.domain.prize;

public class DealerPrize {
    private final int profit;

    public DealerPrize(int tableTotalBetAmount, int playersTotalPrizeAmount) {
        this.profit = tableTotalBetAmount - playersTotalPrizeAmount;
    }

    public int getProfit() {
        return profit;
    }
}
