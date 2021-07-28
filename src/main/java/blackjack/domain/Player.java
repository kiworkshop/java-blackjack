package blackjack.domain;

import lombok.Getter;

import java.util.stream.Collectors;

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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getName())
                .append("카드: ")
                .append(getCards().stream()
                        .map(Card::toString)
                        .collect(Collectors.joining(", ")));
        return sb.toString();
    }
}
