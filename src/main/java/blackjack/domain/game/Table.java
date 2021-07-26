package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Dealer;

public class Table {

    private final Deck deck;
    private final Dealer dealer;

    public Table() {
        this.deck = new Deck();
        this.dealer = new Dealer(deck.drawInitialHands());
    }

    public Hands drawInitialHands() {
        return deck.drawInitialHands();
    }

    public Card deal() {
        return deck.draw();
    }

    public Dealer getDealer() {
        return dealer;
    }

    public int getCardSize() {
        return deck.size();
    }

}
