package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Table {
    public static final int INITIAL_DEAL_COUNT = 2;
    private static final int DRAW_CARD_COUNT_ON_HIT = 1;

    private final Deck deck;
    private final Dealer dealer;
    private final List<Player> players;

    public Table(List<String> playerNames) {
        this.deck = new Deck();
        this.dealer = new Dealer(deck.drawCards(INITIAL_DEAL_COUNT));
        this.players = Collections.unmodifiableList(generatePlayers(playerNames));
    }

    private List<Player> generatePlayers(List<String> playerNames) {
        return playerNames.stream()
                .map(playerName -> new Player(playerName, deck.drawCards(INITIAL_DEAL_COUNT)))
                .collect(Collectors.toList());
    }

    public Player hit(Player player) {
        List<Card> card = deck.drawCards(DRAW_CARD_COUNT_ON_HIT);
        player.take(card);
        return player;
    }

    public void finalDeal() {
        if (dealer.hit()) {
            List<Card> card = deck.drawCards(DRAW_CARD_COUNT_ON_HIT);
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
