package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

import java.util.List;
import java.util.stream.Collectors;

public class Table {
    private final Deck deck;
    private final Dealer dealer;
    private final List<Player> players;

    public Table(List<String> playerNames) {
        this.deck = new Deck();
        this.dealer = new Dealer(deck.drawInitialHands());
        this.players = generatePlayers(playerNames);
    }

    private List<Player> generatePlayers(List<String> playerNames) {
        return playerNames.stream()
                .map(playerName -> new Player(playerName, deck.drawInitialHands()))
                .collect(Collectors.toList());
    }

    public Player hit(Player player) {
        Card card = deck.draw();
        player.take(card);
        return player;
    }

    public void finalDeal() {
        if (dealer.hit()) {
            Card card = deck.draw();
            dealer.take(card);
        }
    }

    public int getDeckSize() {
        return deck.size();
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
