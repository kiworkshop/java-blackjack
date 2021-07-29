package blackjack.domain.prize;

public class PlayerPrize {
    private final String name;
    private final Prize prize;

    public PlayerPrize(String name, Prize prize) {
        this.name = name;
        this.prize = prize;
    }

    public Prize getPrize() {
        return prize;
    }

    public String getName() {
        return name;
    }
}
