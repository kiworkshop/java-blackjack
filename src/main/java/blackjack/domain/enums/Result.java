package blackjack.domain.enums;

public enum Result {
    WIN(1),
    LOSE(-1),
    DRAW(0);

    private final int point;

    Result(int point) {
        this.point = point;
    }

    public int getPoint() {
        return point;
    }
}
