package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;

import java.util.List;
import java.util.stream.Collectors;

public class Table {
    private final Deck deck;
    private final Dealer dealer;
    private final Players players;

    public Table(List<String> playerNames) {
        this.deck = new Deck();
        this.dealer = new Dealer(deck.drawInitialHands());
        this.players = new Players(generatePlayers(playerNames));
    }

    private List<Player> generatePlayers(List<String> playerNames) {
        return playerNames.stream()
                .map(playerName -> new Player(playerName, drawInitialHands()))
                .collect(Collectors.toList());
    }

    public Hands drawInitialHands() {
        return deck.drawInitialHands();
    }

    public Player hit(Player player) {
        Card card = deck.draw();
        player.take(card);
        return player;
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

    public List<Player> getPlayers() {
        return players.getPlayers();
    }
}
