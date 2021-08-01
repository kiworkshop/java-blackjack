package blackjack.domain;

import blackjack.domain.card.Deck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Gamer;
import blackjack.domain.participant.Player;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class GameSystem {
    private static final String DEFAULT_DEALER_NAME = "딜러";

    private final Dealer dealer = new Dealer(DEFAULT_DEALER_NAME, Deck.getTwoCards());
    private final List<Player> players;

    public GameSystem(final List<String> playerNames) {
        this.players = createPlayers(playerNames);
    }

    private List<Player> createPlayers(final List<String> playerNames) {
        return playerNames.stream()
                .map(name -> new Player(name, Deck.getTwoCards()))
                .collect(Collectors.toList());
    }

    public List<String> getPlayerNames() {
        return Collections.unmodifiableList(players.stream()
                .map(Gamer::getName)
                .collect(Collectors.toList()));
    }
}
