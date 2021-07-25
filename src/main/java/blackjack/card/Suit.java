package blackjack.card;

public enum Suit {
    SPADE("스페이드"),
    HEART("하트"),
    DIAMOND("다이아몬드"),
    CLUB("스페이드");

    private String name;

    Suit(String name) {
        this.name = name;
    }
}
