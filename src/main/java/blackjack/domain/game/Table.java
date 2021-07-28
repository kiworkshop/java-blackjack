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

    public Card drawCard() {
        return deck.draw();
    }

    public void finalDeal() {
        if (dealer.hit()) {
            dealer.take(deck.draw());
        }
    }

    public int getCardSize() {
        return deck.size();
    }

    public Dealer getDealer() {
        return dealer;
    }
}
