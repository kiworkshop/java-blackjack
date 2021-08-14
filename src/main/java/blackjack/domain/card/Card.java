package blackjack.domain.card;

import blackjack.exception.NoSuchCardException;

import java.util.*;
import java.util.stream.IntStream;

public class Card {
    private static final int NUMBER_CARD_MIN_RANK = 2;
    private static final int NUMBER_CARD_MAX_RANK = 10;
    private static final int MAJOR_CARD_RANK = 10;
    private static final Collection<String> MAJOR_CARD_SIGNATURES = Arrays.asList("J", "Q", "K");

    private static final Map<String, Card> CARD_CACHE = new HashMap<>();

    static {
        Arrays.stream(Suit.values())
                .forEach(suit -> {
                    IntStream.rangeClosed(NUMBER_CARD_MIN_RANK, NUMBER_CARD_MAX_RANK).forEach(
                            rank -> CARD_CACHE.put(generateKey(suit, rank), numberCardOf(suit, rank)));
                    MAJOR_CARD_SIGNATURES.forEach(
                            signature -> CARD_CACHE.put(generateKey(suit, signature), majorCardOf(suit, signature)));
                    CARD_CACHE.put(generateKey(suit, AceCard.SIGNATURE), aceCardOf(suit));
                });
    }

    private final Suit suit;
    private final int rank;
    private final String signature;

    private Card(Suit suit, int rank, String signature) {
        this.suit = suit;
        this.rank = rank;
        this.signature = signature;
    }

    private static Card numberCardOf(Suit suit, int rank) {
        return new Card(suit, rank, String.valueOf(rank));
    }

    private static Card majorCardOf(Suit suit, String signature) {
        return new Card(suit, MAJOR_CARD_RANK, signature);
    }

    private static Card aceCardOf(Suit suit) {
        return new Card(suit, AceCard.DEFAULT_RANK, AceCard.SIGNATURE);
    }

    private static String generateKey(Suit suit, String signature) {
        return suit.getName() + signature;
    }

    private static String generateKey(Suit suit, int rank) {
        return generateKey(suit, String.valueOf(rank));
    }

    public static Card of(Suit suit, String signature) {
        String key = generateKey(suit, signature);
        Card card = CARD_CACHE.get(key);

        if (Objects.isNull(card)) {
            throw new NoSuchCardException(key);
        }

        return card;
    }

    public static Card of(Suit suit, int rank) {
        return of(suit, String.valueOf(rank));
    }

    public static Collection<Card> getAll() {
        return CARD_CACHE.values();
    }

    public boolean isMajorCard() {
        return MAJOR_CARD_SIGNATURES.contains(signature);
    }

    public boolean isAceCard() {
        return signature.equals(AceCard.SIGNATURE);
    }

    public boolean isNotAceCard() {
        return !isAceCard();
    }

    public Suit getSuit() {
        return suit;
    }

    public int getRank() {
        return rank;
    }

    public String getSignature() {
        return signature;
    }
}
