package blackjack.domain;

import blackjack.domain.enums.Score;
import blackjack.domain.enums.Suit;

import java.util.Objects;

public class Card {
    private final Score score;
    private final Suit suit;

    public Card(final Score score, final Suit suit) {
        this.score = score;
        this.suit = suit;
    }

    public int getScore() {
        return score.getScore();
    }

    public boolean isAce() {
        return score.isAce();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return score == card.score && suit == card.suit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score, suit);
    }
}
