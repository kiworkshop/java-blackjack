package blackjack.domain.card;

import blackjack.domain.game.Hands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class Deck {
    public static final int INITIAL_DEAL_COUNT = 2;

    private final Map<String, Card> deck;

    public Deck(Map<String, Card> deck) {
        this.deck = deck;
    }

    public static Deck of(Map<String, Card> deck) {
        return new Deck(deck);
    }

    public Hands drawInitialHands() {
        List<Card> deals = new ArrayList<>();
        IntStream.range(0, INITIAL_DEAL_COUNT)
                .forEach(n -> deals.add(draw()));
        return new Hands(deals);
    }

    public Card draw() {
        if (deck.isEmpty()) {
            throw new IndexOutOfBoundsException("남아있는 카드가 없습니다.");
        }
        Card card = deck.get(randomCard());
        deck.remove(card.key());
        return card;
    }

    private String randomCard() {
        List<String> keys = new ArrayList<>(deck.keySet());
        Collections.shuffle(keys);
        return keys.get(0);
    }

    public int size() {
        return deck.size();
    }
}
