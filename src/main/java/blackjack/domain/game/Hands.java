package blackjack.domain.game;

import blackjack.domain.card.AceCard;
import blackjack.domain.card.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static blackjack.domain.prize.PrizeResults.BLACKJACK_RANK;

public class Hands {
    private final List<Card> hands;

    public Hands(List<Card> cards) {
        this.hands = new ArrayList<>(cards);
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
            return AceCard.softOrHardSum(sumExceptAceCards, aceCardCount);
        }
        return sumExceptAceCards;
    }

    private int calculateSumExceptAceCards() {
        return hands.stream()
                .filter(((Predicate<? super Card>) AceCard.class::isInstance).negate())
                .mapToInt(Card::getRank)
                .sum();
    }

    private int countAceCards() {
        return (int) hands.stream()
                .filter(AceCard.class::isInstance)
                .count();
    }

    public boolean bust() {
        return sumRanks() > BLACKJACK_RANK;
    }

    public boolean blackjack() {
        return countAceCards() == 1 && countMajorCards() == 1;
    }

    private int countMajorCards() {
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
