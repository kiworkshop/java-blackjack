package blackjack.domain;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Dealer {

    private static final String DEALER_NAME = "딜러";
    private static final int DEALER_DRAW_THRESHOLD = 16;

    String name;
    List<Card> cards;

    public Dealer() {
        this.name = DEALER_NAME;
        this.cards = new ArrayList<>();
    }

    public void drawOrNot(Deck deck) {
        if (Score.getCardsSum(cards).getScore() <= Score.of(DEALER_DRAW_THRESHOLD).getScore()) {
            this.cards.add(deck.drawCard());
        }
    }


}
