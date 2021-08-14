package blackjack.domain.card;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Deck {
    static final int TWO_CARDS = 2;
    private final Deque<Card> deck;

    public Deck() {
        List<Card> cards = new ArrayList<>(Card.getDeck());
        Collections.shuffle(cards);
        this.deck = new ArrayDeque<>(cards);
    }

    public Card getCard() {
        return deck.pop();
    }

    public GivenCards getTwoCards() {
        return new GivenCards(Collections.unmodifiableList(IntStream.range(0, TWO_CARDS)
                .mapToObj(i -> deck.pop())
                .collect(Collectors.toList())));
    }
}
