package blackjack.domain.prize;

public class DealerPrize {
    private int winCount;
    private int loseCount;
    private int tieCount;

    public DealerPrize() {
        this.winCount = 0;
        this.loseCount = 0;
        this.tieCount = 0;
    }

    public void incrementWinCount() {
        winCount++;
    }

    public void incrementLoseCount() {
        loseCount++;
    }

    public void incrementTieCount() {
        tieCount++;
    }

    public int getWinCount() {
        return winCount;
    }

    public int getLoseCount() {
        return loseCount;
    }

    public int getTieCount() {
        return tieCount;
    }
}
