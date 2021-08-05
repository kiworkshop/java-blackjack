package blackjack.domain.prize;

public class Profit {
    private final int receiveAmount;
    private final int profit;

    public Profit(int betAmount, Prize prize) {
        this.receiveAmount = calculateReceiveAmount(betAmount, prize);
        this.profit = calculateProfit(betAmount);
    }

    private int calculateReceiveAmount(int betAmount, Prize prize) {
        return (int) (betAmount * prize.getMultiple());
    }

    private int calculateProfit(int betAmount) {
        return this.receiveAmount - betAmount;
    }

    public int getReceiveAmount() {
        return receiveAmount;
    }

    public int getProfit() {
        return profit;
    }
}
