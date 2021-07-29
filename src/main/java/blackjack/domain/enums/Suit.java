package blackjack.domain.enums;

public enum Suit {
    SPADE("스페이드"),
    DIAMOND("다이아몬드"),
    CLUB("클로버"),
    HEART("하트");

    private final String suit;

    Suit(final String suit) {
        this.suit = suit;
    }

    public String getSuit() {
        return suit;
    }
}
