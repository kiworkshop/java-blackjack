package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;

import java.util.ArrayList;
import java.util.List;

public class Hands {
    private static final int HARD_HAND = 1;
    private static final int SOFT_HAND = 11;

    private final List<Card> cards;

    public Hands(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }

    public int sumRank() {
        int rankSum = sumExceptAceCards();
        int aceCount = countAceCards();

        if (aceCount > 0) {
            return softOrHardSum(rankSum, aceCount);
        }
        return rankSum;
    }

    private int sumExceptAceCards() {
        return cards.stream()
                .filter(card -> !Denomination.isAceCard(card))
                .mapToInt(Card::rank)
                .sum();
    }

    public int softOrHardSum(int rankSum, int aceCount) {
        if (softAvailable(rankSum, aceCount)) {
            return softSum(rankSum, aceCount);
        }
        return hardSum(rankSum, aceCount);
    }

    private boolean softAvailable(int rankSum, int aceCount) {
        int threshold = SOFT_HAND - aceCount;
        return rankSum <= threshold;
    }

    private int softSum(int rankSum, int aceCount) {
        int hardHandCount = aceCount - 1;
        return rankSum + (hardHandCount * HARD_HAND + SOFT_HAND);
    }

    private int hardSum(int sumRank, int aceCount) {
        return sumRank + (aceCount * HARD_HAND);
    }

    public int countAceCards() {
        return (int) cards.stream()
                .filter(Denomination::isAceCard)
                .count();
    }

    public int countMajorCards() {
        return (int) cards.stream()
                .filter(Denomination::isMajorCard)
                .count();
    }

    public Card firstHand() {
        return cards.get(0);
    }

    public int size() {
        return cards.size();
    }

    public List<Card> cards() {
        return cards;
    }
}
