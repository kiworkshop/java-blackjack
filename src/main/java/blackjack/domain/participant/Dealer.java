package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import lombok.Getter;

import java.util.stream.Collectors;

@Getter
public class Dealer extends Participant {

    private static final String DEALER_NAME = "딜러";
    private static final int DEALER_DRAW_THRESHOLD = 17;

    public Dealer() {
        super(DEALER_NAME);
    }

    public void drawOrNot(Deck deck) {
        if (drawable()) {
            receiveCard(deck.drawCard());
        }
    }

    @Override
    public boolean drawable() {
        return getCardsSum() < DEALER_DRAW_THRESHOLD;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(DEALER_NAME)
                .append(": ")
                .append(getCards().stream()
                        .map(Card::toString)
                        .collect(Collectors.joining(", ")));
        return sb.toString();
    }
}
