package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.game.Hands;

public class Player {
    private final String name;
    private Hands hands;

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
