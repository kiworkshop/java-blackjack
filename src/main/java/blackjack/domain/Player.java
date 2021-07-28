package blackjack.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static blackjack.domain.GameService.ACE_BONUS_SCORE;
import static blackjack.domain.GameService.BLACKJACK;

public class Player {

    private final String name;
    private final List<Card> cards = new ArrayList<>();

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void addFirstTwoCards(List<Card> firstTwoCards) {
        if (firstTwoCards.size() != 2) {
            throw new IllegalArgumentException("두장의 카드만 지급이 가능합니다");
        }

        cards.addAll(firstTwoCards);
    }

    public int sumScore() {
        int sum = cards.stream()
                .mapToInt(Card::getScore)
                .sum();

        if (hasAce() && sum + ACE_BONUS_SCORE <= BLACKJACK) {
            sum += ACE_BONUS_SCORE;
        }

        return sum;
    }

    public boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    public boolean isBurst(int sum) {
        return sum > BLACKJACK;

    }
}
