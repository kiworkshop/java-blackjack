package blackjack.domain;

public class Player extends Participant {
    private final int bettingMoney;

    public int getBettingMoney() {
        return bettingMoney;
    }

    public Player(String name) {
        super(name);
        bettingMoney = 0;
    }
    public Player(String name, int bettingMoney) {
        super(name);
        this.bettingMoney = bettingMoney;
    }

}
