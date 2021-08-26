package blackjack.domain.participant;

import blackjack.domain.card.Card;

import java.util.List;

public interface BlackjackRule {

    void draw(Card card);

    boolean hit();

    boolean bust();

    boolean blackjack();

    int countHands();

    List<Card> cards();

}