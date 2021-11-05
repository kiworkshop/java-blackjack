package blackjack.domain.prize;

public class PlayerPrize {
    private final String name;
    private final Prize prize;

    public PlayerPrize(String playerName, Prize prize) {
        this.name = playerName;
        this.prize = prize;
    }

    public Prize value() {
        return prize;
    }

    public String name() {
        return name;
    }
}
