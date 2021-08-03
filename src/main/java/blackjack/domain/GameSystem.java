package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.GivenCards;
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

    protected GameSystem(final List<Player> players) {
        this.players = players;
    }

    public static GameSystem create(final List<String> playerNames) {
        return new GameSystem(playerNames.stream()
                .map(name -> new Player(name, Deck.getTwoCards()))
                .collect(Collectors.toList()));
    }

    public List<String> getPlayerNames() {
        return Collections.unmodifiableList(players.stream()
                .map(Gamer::getName)
                .collect(Collectors.toList()));
    }

    public List<Card> getDealerCards() {
        return Collections.unmodifiableList(dealer.getCards().list());
    }

    public List<List<Card>> getPlayerCards() {
        return Collections.unmodifiableList(players.stream()
                .map(Gamer::getCards)
                .map(GivenCards::list)
                .collect(Collectors.toList()));
    }

    public boolean isFinished() {
        return players.stream()
                .allMatch(Gamer::isFinished);
    }

    public String getCurrentPlayer() {
        return players.stream()
                .filter(player -> !player.isFinished())
                .map(Gamer::getName)
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }

    public void hit(final String name) {
        Player fetchedPlayer = findPlayerBy(name);
        fetchedPlayer.hit(Deck.getCard());
    }

    private Player findPlayerBy(final String name) {
        return players.stream()
                .filter(player -> player.getName().equals(name))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
