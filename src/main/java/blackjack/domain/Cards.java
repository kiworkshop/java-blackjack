package blackjack.domain;

import blackjack.service.GameService;

import java.util.List;

public class Cards {
    private final List<Card> cards;

    public Cards(List<Card> cards) {
        this.cards = cards;
    }

    public List<Card> getCards() {
        return cards;
    }

    public boolean isBust() {
        return getCardScore() > GameService.BLACKJACK;
    }

    public boolean isStay() {
        return getCardScore() == GameService.BLACKJACK && cards.size() > 2;
    }

    public boolean isBlackJack() {
        return (getCardScore() == GameService.BLACKJACK) && hasAce() && cards.size() == 2;
    }

    public int getCardScore() {
        int sum = cards.stream()
                .mapToInt(Card::getScore)
                .sum();

        if (hasAce() && sum + GameService.ACE_BONUS_SCORE <= GameService.BLACKJACK) {
            sum += GameService.ACE_BONUS_SCORE;
        }

        return sum;
    }

    private boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }
}
