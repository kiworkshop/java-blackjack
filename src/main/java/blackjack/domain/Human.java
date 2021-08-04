package blackjack.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Human {

    private final List<Card> cards = new ArrayList<>();
    private final String name;

    public Human() {
        this("딜러");
    }

    public Human(String name) {
        this.name = name;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public String getName() {
        return name;
    }
}
