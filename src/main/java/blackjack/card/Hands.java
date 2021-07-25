package blackjack.card;

import java.util.ArrayList;
import java.util.List;

public class Hands {
    private final List<Card> hands;

    public Hands(List<Card> cards) {
        this.hands = new ArrayList<>();
        this.hands.addAll(cards);
    }

    public void addCard(Card card) {
        this.hands.add(card);
    }

    public int size() {
        return hands.size();
    }

    public int sumRanks() {
        return hands.stream()
                .mapToInt(Card::getRank)
                .sum();
    }

    public Card getFirstHand() {
        return hands.get(0);
    }
}
