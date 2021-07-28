package blackjack.domain;

import blackjack.enums.Denomination;
import blackjack.enums.Type;
import lombok.Getter;

import java.util.Objects;

@Getter
public class Card {

    private final Denomination denomination;
    private final Type type;

    public Card(Denomination denomination, Type type) {
        this.denomination = denomination;
        this.type = type;
    }

    public boolean isAce() {
        return denomination.equals(Denomination.ACE);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Card)) {
            return false;
        }
        Card card = (Card) o;
        return denomination == card.denomination &&
                type == card.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(denomination, type);
    }

    @Override
    public String toString() {
        return denomination.getDenominationName() + type.getType();

    }
}
