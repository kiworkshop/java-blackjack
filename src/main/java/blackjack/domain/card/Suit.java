package blackjack.domain.card;

public enum Suit {
    SPADE("스페이드"),
    HEART("하트"),
    DIAMOND("다이아몬드"),
    CLUB("스페이드");

    private final String suit;

    Suit(String suit) {
        this.suit = suit;
    }

    public String value() {
        return suit;
    }
}
