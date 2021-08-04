package blackjack.domain;

import blackjack.enums.Denomination;
import blackjack.enums.Suit;

public class Card {
    private final Denomination denomination;
    private final Suit suit;
    private final String name;

    public Card(Denomination denomination, Suit suit) {
        this.denomination = denomination;
        this.suit = suit;
        this.name = denomination.getDenomination() + suit.getType();
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return denomination.getScore();
    }

    public boolean isAce() {
        return denomination.equals(Denomination.A);
    }
}
