package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Participant;
import blackjack.domain.result.Rule;
import blackjack.domain.result.WinningResult;
import lombok.Getter;

import java.util.Arrays;
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

    public WinningResult win(Dealer dealer) {
        return Arrays.stream(Rule.values())
                .filter(rule -> rule.compare(this, dealer))
                .findFirst()
                .get()
                .getWinningResult();
    }
}
