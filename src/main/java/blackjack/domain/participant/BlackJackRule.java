package blackjack.domain.participant;

import blackjack.domain.card.Card;

import java.util.List;

public interface BlackJackRule {
    void take(Card card);

    int sumRank();

    boolean hit();

    boolean bust();

    boolean blackjack();

    int countHands();

    List<Card> getCards();
}