package blackjack.domain.participant;

import blackjack.domain.card.Card;

import java.util.List;

public interface BlackjackRule {
    int sumRank();

    void draw(Card card);

    boolean hit();

    boolean bust();

    boolean blackjack();

    int countHands();

    List<Card> getCards();
}