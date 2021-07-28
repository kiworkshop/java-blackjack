package blackjack.domain;

import blackjack.enums.CardScore;
import blackjack.enums.CardType;

public class Card {
    private final CardScore score;
    private final CardType type;

    public Card(CardScore score, CardType type) {
        this.score = score;
        this.type = type;
    }

    public int getScore() {
        return score.getScore();
    }

    public boolean isAce() {
        return score.equals(CardScore.A);
    }
}
