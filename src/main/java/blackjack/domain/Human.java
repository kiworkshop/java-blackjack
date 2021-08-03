package blackjack.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Human {

    private final List<Card> cards = new ArrayList<>();
    
    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public void addCard(Card card) {
        cards.add(card);
    }
}
