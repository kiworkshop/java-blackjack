package blackjack.domain.game;

import blackjack.domain.card.AceCard;
import blackjack.domain.card.Card;

import java.util.ArrayList;
import java.util.List;

import static blackjack.domain.prize.PrizeResults.BLACKJACK_RANK;

public class Hands {
    private final List<Card> hands;

    public Hands(List<Card> cards) {
        this.hands = new ArrayList<>();
        this.hands.addAll(cards);
    }

    public void addCard(Card card) {
        this.hands.add(card);
    }

    public int size() {
        return hands.size();
    }

    public int sumRanks() {
        int sumExceptAceCards = calculateSumExceptAceCards();
        int aceCardCount = countAceCards();

        if (aceCardCount > 0) {
            return softOrHardSum(sumExceptAceCards, aceCardCount);
        }
        return sumExceptAceCards;
    }

    private int softOrHardSum(int sumExceptAceCards, int aceCardCount) {
        int threshold = 11 - aceCardCount;
        if (sumExceptAceCards <= threshold) {
            return (aceCardCount - 1) + 11 + sumExceptAceCards;
        }
        return aceCardCount + sumExceptAceCards;
    }

    private int countAceCards() {
        return (int) hands.stream()
                .filter(AceCard.class::isInstance).count();
    }

    private int calculateSumExceptAceCards() {
        return hands.stream()
                .filter(card -> !card.getSignature().equals(AceCard.SIGNATURE))
                .mapToInt(Card::getRank)
                .sum();
    }

    public boolean bust() {
        return sumRanks() > BLACKJACK_RANK;
    }

    public boolean blackjack() {
        if (countAceCards() != 1) {
            return false;
        }

        return (majorCardCount() == 1);
    }

    private int majorCardCount() {
        return (int) hands.stream()
                .filter(Card::majorCard)
                .count();
    }

    public Card getFirstHand() {
        return hands.get(0);
    }

    public List<Card> getHands() {
        return hands;
    }
}
