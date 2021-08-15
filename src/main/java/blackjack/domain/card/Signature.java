package blackjack.domain.card;

public enum Signature {
    ACE("A", 11),
    JACK("J", 10),
    QUEEN("Q", 10),
    KING("K", 10),
    NUMBER2("2", 2),
    NUMBER3("3", 3),
    NUMBER4("4", 4),
    NUMBER5("5", 5),
    NUMBER6("6", 6),
    NUMBER7("7", 7),
    NUMBER8("8", 8),
    NUMBER9("9", 9),
    NUMBER10("10", 10);

    private static final int HARD_RANK = 1;
    private static final int SOFT_RANK = 11;

    private final String symbol;
    private final int rank;

    Signature(String name, int rank) {
        this.symbol = name;
        this.rank = rank;
    }

    public static boolean isMajor(Signature signature) {
        return signature == JACK || signature == QUEEN || signature == KING;
    }

    public static boolean isAce(Signature signature) {
        return signature == ACE;
    }

    public static int sumAceCards(int sumExceptAceCards, int aceCardCount) {
        if (softRankAvailable(sumExceptAceCards, aceCardCount)) {
            return SOFT_RANK + (aceCardCount - 1) * HARD_RANK;
        }
        return aceCardCount * HARD_RANK;
    }

    private static boolean softRankAvailable(int sumExceptAceCards, int aceCardCount) {
        int threshold = SOFT_RANK - aceCardCount;
        return sumExceptAceCards <= threshold;
    }

    public String getSymbol() {
        return symbol;
    }

    public int getRank() {
        return rank;
    }
}