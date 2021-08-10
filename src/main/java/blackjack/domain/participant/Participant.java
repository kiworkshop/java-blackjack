package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
public abstract class Participant {
    private static final int DIFFERENCE_OF_ACE_SCORE = 10;
    private static final int BLACKJACK = 21;
    private static final int ZERO = 0;
    private static final String CHECK_NULL_OR_EMPTY = "이름이 빈 칸 혹은 null 값이 아닌지 확인해주세요.";
    private static final String CHECK_CONTAINING_ONLY_LETTERS_AND_DIGITS = "이름은 특수문자를 포함하지 않은 문자와 숫자로 지정해주세요.";

    private final String name;
    private final List<Card> cards;

    protected Participant(String name) {
        validateName(name);

        this.name = name;
        this.cards = new ArrayList<>();
    }

    private void validateName(String name) {
        validateNullOrEmpty(name);
        validateAlphaNumeric(name);
    }

    private void validateNullOrEmpty(String name) {
        if (StringUtils.isBlank(name)) {
            throw new IllegalArgumentException(CHECK_NULL_OR_EMPTY);
        }
    }

    private void validateAlphaNumeric(String name) {
        if (!StringUtils.isAlphanumericSpace(name)) {
            throw new IllegalArgumentException(CHECK_CONTAINING_ONLY_LETTERS_AND_DIGITS);
        }
    }

    protected abstract boolean drawable();

    public void receiveCard(Card card) {
        cards.add(card);
    }

    public int getCardsSum() {
        int scoreOfAceAsEleven = sumOfCardsScore();
        int aceCount = getAceCount();

        while (canCountAceAsOne(scoreOfAceAsEleven, aceCount)) {
            scoreOfAceAsEleven = scoreOfAceAsOne(scoreOfAceAsEleven);
            aceCount--;
        }

        return scoreOfAceAsEleven;
    }

    private int scoreOfAceAsOne(int scoreOfAceAsEleven) {
        return scoreOfAceAsEleven - DIFFERENCE_OF_ACE_SCORE;
    }

    private boolean canCountAceAsOne(int scoreOfAceAsEleven, int aceCount) {
        return scoreOfAceAsEleven > BLACKJACK && aceCount > ZERO;
    }

    private int getAceCount() {
        return (int) cards.stream()
                .filter(Card::isAce)
                .count();
    }

    private int sumOfCardsScore() {
        return cards.stream()
                .map(Card::getDenomination)
                .mapToInt(Denomination::getScore)
                .sum();
    }

    public boolean isBust() {
        return getCardsSum() > BLACKJACK;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Participant that = (Participant) o;
        return Objects.equals(name, that.name) && Objects.equals(cards, that.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cards);
    }
}
