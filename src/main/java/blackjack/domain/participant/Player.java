package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.game.Hands;

public class Player {
    private final String name;
    private Hands hands;

    public Player(String name) {
        this.name = name;
        this.hands = new Hands();
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
