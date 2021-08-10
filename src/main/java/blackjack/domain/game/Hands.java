package blackjack.domain.game;

import blackjack.domain.card.AceCard;
import blackjack.domain.card.Card;

import java.util.ArrayList;
import java.util.List;

public class Hands {
    private final List<Card> hands;

    public Hands(List<Card> cards) {
        this.hands = new ArrayList<>(cards);
    }

    public void add(List<Card> card) {
        this.hands.addAll(card);
    }

    public void add(Card card) {
        this.hands.add(card);
    }

    public int size() {
        return hands.size();
    }

    public int sumRanks() {
        int sumExceptAceCards = calculateSumExceptAceCards();
        int aceCardCount = countAceCards();

        if (aceCardCount > 0) {
            return AceCard.softOrHardSum(sumExceptAceCards, aceCardCount);
        }
        return sumExceptAceCards;
    }

    private int calculateSumExceptAceCards() {
        return hands.stream()
                .filter(Card::isNotAceCard)
                .mapToInt(Card::getRank)
                .sum();
    }

    public boolean hasOneAceCard() {
        return countAceCards() == 1;
    }

    private int countAceCards() {
        return (int) hands.stream()
                .filter(Card::isAceCard)
                .count();
    }

    public boolean hasOneMajorCard() {
        return countMajorCards() == 1;
    }

    private int countMajorCards() {
        return (int) hands.stream()
                .filter(Card::isMajorCard)
                .count();
    }

    public Card getFirstHand() {
        return hands.get(0);
    }

    public List<Card> getHands() {
        return new ArrayList<>(hands);
    }
}
