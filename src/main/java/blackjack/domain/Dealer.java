package blackjack.domain;

import lombok.Getter;

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
    protected boolean drawable() {
        return getCardsSum() < DEALER_DRAW_THRESHOLD;
    }
}
