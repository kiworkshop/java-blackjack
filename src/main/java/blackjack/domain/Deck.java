package blackjack.domain;

import blackjack.domain.enums.Score;
import blackjack.domain.enums.Suit;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Deck {
    static final int TWO_CARDS = 2;
    private static final Deque<Card> cards = new ArrayDeque<>();

    static {
        List<Card> cards = new ArrayList<>();

        for (final Score score : Score.values()) {
            Arrays.stream(Suit.values())
                    .forEach(suit -> cards.add(new Card(score, suit)));
        }

        Collections.shuffle(cards);
        Deck.cards.addAll(cards);
    }

    private Deck() {
    }

    public static Card getCard() {
        return cards.pop();
    }

    public static GivenCards getTwoCards() {
        return new GivenCards(Collections.unmodifiableList(IntStream.range(0, TWO_CARDS)
                .mapToObj(i -> cards.pop())
                .collect(Collectors.toList())));
    }
}
