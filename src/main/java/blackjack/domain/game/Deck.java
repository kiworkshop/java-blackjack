package blackjack.domain.game;

import blackjack.domain.card.AceCard;
import blackjack.domain.card.Card;
import blackjack.domain.card.Major;
import blackjack.domain.card.Suit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Deck {
    public static final int INITIAL_DEAL_COUNT = 2;
    public static final int TOTAL_CARD_COUNT = 52;
    private static final int MIN_NUMBER_RANK = 2;
    private static final int MAX_NUMBER_RANK = 10;

    private final List<Card> cards;

    public Deck() {
        this.cards = new ArrayList<>();
        generateCards();
        Collections.shuffle(cards);
    }

    private void generateCards() {
        for (Suit suit : Suit.values()) {
            cards.addAll(generateNumberCards(suit));
            cards.addAll(generateMajorCards(suit));
            cards.add(new AceCard(suit));
        }
    }

    private List<Card> generateMajorCards(Suit suit) {
        return Arrays.stream(Major.values())
                .map(signature -> new Card(suit, signature.name()))
                .collect(Collectors.toList());
    }

    private List<Card> generateNumberCards(Suit suit) {
        return IntStream.rangeClosed(MIN_NUMBER_RANK, MAX_NUMBER_RANK)
                .mapToObj(signature -> new Card(suit, signature))
                .collect(Collectors.toList());
    }

    public Hands drawInitialHands() {
        List<Card> deals = new ArrayList<>();
        IntStream.range(0, INITIAL_DEAL_COUNT)
                .forEach(n -> deals.add(draw()));
        return new Hands(deals);
    }

    public Card draw() {
        if (cards.isEmpty()) {
            throw new IndexOutOfBoundsException("남아있는 카드가 없습니다.");
        }

        Card randomCard = cards.get(0);
        cards.remove(randomCard);
        return randomCard;
    }

    public int size() {
        return cards.size();
    }
}
