package blackjack.domain.game;

import blackjack.domain.card.Card;

import java.util.Deque;

public interface DeckGenerator {
    Deque<Card> generateDeck();
}
