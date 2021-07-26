package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.game.Hands;

import java.util.List;

import static blackjack.domain.game.Deck.INITIAL_DEAL_COUNT;

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

    public boolean isNeverHit() {
        return this.countHands() == INITIAL_DEAL_COUNT;
    }

    public List<Card> getCards() {
        return hands.getHands();
    }
}
