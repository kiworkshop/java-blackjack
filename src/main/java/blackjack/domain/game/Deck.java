package blackjack.domain.game;

import blackjack.domain.card.AceCard;
import blackjack.domain.card.Card;
import blackjack.domain.card.Suit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Deck {
    public static final int TOTAL_CARD_COUNT = 52;
    private static final int MIN_NUMBER_RANK = 2;
    private static final int MAX_NUMBER_RANK = 10;
    private static final List<String> MAJOR_SIGNATURES = Arrays.asList("K", "Q", "J");

    private final List<Card> cards;

    public Deck() {
        this.cards = generateCards();
        Collections.shuffle(cards);
    }

    private List<Card> generateCards() {
        List<Card> cards = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            cards.addAll(generateNumberCards(suit));
            cards.addAll(generateMajorCards(suit));
            cards.add(new AceCard(suit));
        }
        return cards;
    }

    private List<Card> generateMajorCards(Suit suit) {
        return MAJOR_SIGNATURES.stream()
                .map(signature -> new Card(suit, signature))
                .collect(Collectors.toList());
    }

    private List<Card> generateNumberCards(Suit suit) {
        return IntStream.rangeClosed(MIN_NUMBER_RANK, MAX_NUMBER_RANK)
                .mapToObj(signature -> new Card(suit, signature))
                .collect(Collectors.toList());
    }

    public List<Card> drawCards(int count) {
        List<Card> deals = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            deals.add(drawCard());
        }
        return deals;
    }

    private Card drawCard() {
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
