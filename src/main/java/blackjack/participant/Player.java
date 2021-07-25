package blackjack.participant;

import blackjack.card.Card;
import blackjack.card.Hands;

public class Player {
    private final String name;
    private final Hands hands;

    public Player(String name, Hands hands) {
        this.name = name;
        this.hands = hands;
    }

    public String getName() {
        return name;
    }

    public int countHands() {
        return hands.size();
    }

    public void hit(Card card) {
        hands.addCard(card);
    }
}
