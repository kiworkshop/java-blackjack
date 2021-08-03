package blackjack.domain;

import blackjack.enums.Denomination;
import blackjack.enums.Suit;

public class Card {
    private final Denomination denomination;
    private final Suit suit;

    public Card(Denomination denomination, Suit suit) {
        this.denomination = denomination;
        this.suit = suit;
    }

    public int getScore() {
        return denomination.getScore();
    }


}
