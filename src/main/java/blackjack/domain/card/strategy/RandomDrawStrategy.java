package blackjack.domain.card.strategy;

import blackjack.domain.card.Card;

import java.util.List;
import java.util.Random;

public class RandomDrawStrategy implements DrawStrategy {
    private static final Random RANDOM = new Random();

    @Override
    public int getNextIndex(List<Card> deck) {
        return RANDOM.nextInt(deck.size());
    }
}
