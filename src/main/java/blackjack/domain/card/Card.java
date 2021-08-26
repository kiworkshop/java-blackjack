package blackjack.domain.card;

public class Card {
    private final Suit suit;
    private final Denomination denomination;

    private Card(Suit suit, Denomination denomination) {
        this.suit = suit;
        this.denomination = denomination;
    }

    public static Card of(Suit suit, Denomination denomination) {
        return new Card(suit, denomination);
    }

    public Denomination denomination() {
        return denomination;
    }

    public int rank() {
        return denomination.rank();
    }

    public String key() {
        return suit + "_" + denomination.signature();
    }
}
