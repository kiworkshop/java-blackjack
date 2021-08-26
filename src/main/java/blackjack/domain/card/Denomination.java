package blackjack.domain.card;

import java.util.EnumSet;

public enum Denomination {
    ACE("A", 1),
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    TEN("10", 10),
    QUEEN("Q", 10),
    JACK("J", 10),
    KING("K", 10);

    private final String signature;
    private final int rank;

    Denomination(String signature, int rank) {
        this.signature = signature;
        this.rank = rank;
    }

    public static boolean isNumberCard(Card card) {
        EnumSet<Denomination> major = EnumSet.of(Denomination.TWO, Denomination.THREE, Denomination.FOUR, Denomination.FIVE,
                Denomination.SIX, Denomination.SEVEN, Denomination.EIGHT, Denomination.NINE, Denomination.TEN);
        return major.contains(card.denomination());
    }

    public static boolean isAceCard(Card card) {
        EnumSet<Denomination> major = EnumSet.of(Denomination.ACE);
        return major.contains(card.denomination());
    }

    public static boolean isMajorCard(Card card) {
        EnumSet<Denomination> major = EnumSet.of(Denomination.QUEEN, Denomination.JACK, Denomination.KING);
        return major.contains(card.denomination());
    }

    public String signature() {
        return signature;
    }

    public int rank() {
        return rank;
    }
}
