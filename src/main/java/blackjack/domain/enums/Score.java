package blackjack.domain.enums;

public enum Score {
    A("A", 1),
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    TEN("10", 10),
    J("J", 10),
    Q("Q", 10),
    K("K", 10);

    private final String denomination;
    private final int score;

    Score(final String denomination, final int score) {
        this.denomination = denomination;
        this.score = score;
    }

    public String getDenomination() {
        return denomination;
    }

    public int getScore() {
        return score;
    }
}
