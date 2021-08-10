package blackjack.domain.card;

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
                    IntStream.rangeClosed(NUMBER_CARD_MIN_RANK, NUMBER_CARD_MAX_RANK)
                            .forEach(rank -> CARD_CACHE.put(generateKey(suit, rank), new Card(suit, rank)));
                    MAJOR_CARD_SIGNATURES.forEach(
                            signature -> CARD_CACHE.put(generateKey(suit, signature), new Card(suit, signature))
                    );
                    CARD_CACHE.put(generateKey(suit, AceCard.SIGNATURE), new Card(suit, AceCard.DEFAULT_RANK, AceCard.SIGNATURE));
                });
    }

    private static String generateKey(Suit suit, String signature) {
        return suit.getName() + signature;
    }

    private static String generateKey(Suit suit, int rank) {
        return generateKey(suit, String.valueOf(rank));
    }

    public static Card get(Suit suit, String signature) {
        Card card = CARD_CACHE.get(generateKey(suit, signature));

        if (Objects.isNull(card)) {
            throw new NoSuchElementException();
        }

        return card;
    }

    public static Card get(Suit suit, int rank) {
        return get(suit, String.valueOf(rank));
    }

    public static Collection<Card> getAll() {
        return CARD_CACHE.values();
    }

    private final Suit suit;
    private final int rank;
    private final String signature;

    private Card(Suit suit, int rank, String signature) {
        this.suit = suit;
        this.rank = rank;
        this.signature = signature;
    }

    private Card(Suit suit, int rank) {
        this(suit, rank, String.valueOf(rank));
    }

    private Card(Suit suit, String signature) {
        this(suit, MAJOR_CARD_RANK, signature);
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

    public int getRank() {
        return rank;
    }

    public String getSignature() {
        return signature;
    }

    public Suit getSuit() {
        return suit;
    }
}
