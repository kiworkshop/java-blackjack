package blackjack.domain;

import blackjack.enums.Denomination;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
public abstract class Participant {
    private static final int DIFFERENCE_OF_ACE_SCORE = 10;
    private static final int BLACKJACK = 21;
    private static final int ZERO = 0;

    private final String name;
    private final List<Card> cards;

    public Participant(String name) {
        // validate(name)

        this.name = name;
        this.cards = new ArrayList<>();
    }

//  private void validate(String name){
//      if (StringUtils.isBlank(name)) {
//          throw new IllegalArgumentException("이름이 빈 칸 혹은 null 값이 아닌지 확인해주세요.");
//        }
//    }

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
