package blackjack.domain;

import lombok.Getter;

@Getter
public class Player extends Participant {

    private static final int PLAYER_DRAW_THRESHOLD = 21;

    public Player(String name) {
        super(name);
    }

    @Override
    public boolean drawable() {
        return getCardsSum() < PLAYER_DRAW_THRESHOLD;
    }
}
