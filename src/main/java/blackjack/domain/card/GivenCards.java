package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static blackjack.domain.GameSystem.BLACKJACK;
import static blackjack.domain.card.Deck.TWO_CARDS;
import static blackjack.domain.enums.Score.ACE_BONUS;

public class GivenCards {
    private final List<Card> cards;

    public GivenCards(final List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public int sum() {
        int sum = cards.stream()
                .mapToInt(Card::getScore)
                .sum();

        if (hasAce() && isLessThanOrEqualToBlackjack(addAceBonus(sum))) {
            sum = addAceBonus(sum);
        }

        return sum;
    }

    private boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    private boolean isLessThanOrEqualToBlackjack(final int sum) {
        return BLACKJACK >= sum;
    }

    private int addAceBonus(final int sum) {
        return sum + ACE_BONUS;
    }

    public boolean isBlackjack() {
        return (cards.size() == TWO_CARDS) && isMaximumThreshold();
    }

    public boolean isBurst() {
        return BLACKJACK < sum();
    }

    public List<Card> list() {
        return Collections.unmodifiableList(cards);
    }

    public void add(final Card card) {
        cards.add(card);
    }

    public boolean isGreaterThan(final GivenCards otherCards) {
        return sum() > otherCards.sum();
    }

    public boolean isLessThan(final GivenCards otherCards) {
        return sum() < otherCards.sum();
    }

    public boolean isLessThanEqualTo(final int value) {
        return sum() <= value;
    }

    public boolean isMaximumThreshold() {
        return sum() == BLACKJACK;
    }
}
