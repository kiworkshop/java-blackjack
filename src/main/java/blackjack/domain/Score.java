package blackjack.domain;

import blackjack.enums.Denomination;

import java.util.List;
import java.util.Objects;

public class Score {

    private final int score;

    private Score(int score) {
        this.score = score;
    }

    public static Score of(int score) {
        return new Score(score);
    }

    public int getScore() {
        return score;
    }

    public static Score getCardsSum(List<Card> cards) {
        int cardsSum = cards.stream()
                .map(Card::getDenomination)
                .mapToInt(Denomination::getScore)
                .sum();

        return new Score(cardsSum);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score1 = (Score) o;
        return score == score1.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score);
    }
}
