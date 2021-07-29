package blackjack.domain.prize;

public class PlayerPrize {
    private final String playerName;    // name -> playerName 변경
    private final Prize prize;

    public PlayerPrize(String playerName, Prize prize) {
        this.playerName = playerName;
        this.prize = prize;
    }

    public Prize getPrize() {
        return prize;
    }

    public String getPlayerName() {
        return playerName;
    }
}
