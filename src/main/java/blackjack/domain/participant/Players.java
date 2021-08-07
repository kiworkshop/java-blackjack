package blackjack.domain.participant;

import blackjack.domain.card.Deck;
import blackjack.domain.card.GivenCards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Players {
    private final Map<String, Person> players = new LinkedHashMap<>();

    public Players(final List<Person> players) {
        converToMap(players);
    }

    public Players(final List<String> playerNames, final Deck deck) {
        this(createPlayers(playerNames, deck));
    }

    private static List<Person> createPlayers(final List<String> playerNames, final Deck deck) {
        return playerNames.stream()
                .map(playerName -> new Player(playerName, deck.getTwoCards()))
                .collect(Collectors.toList());
    }

    private void converToMap(List<Person> players) {
        players.forEach(player -> this.players.put(player.getName(), player));
    }

    public List<String> getPlayerNames() {
        return Collections.unmodifiableList(new ArrayList<>(players.keySet()));
    }

    public List<GivenCards> getPlayerCards() {
        return Collections.unmodifiableList(players.values()
                .stream()
                .map(Person::getCards)
                .collect(Collectors.toList()));
    }

    public boolean allPlayersFinished() {
        return players.values()
                .stream()
                .allMatch(Person::isFinished);
    }

    public String getCurrentPlayer() {
        return players.values()
                .stream()
                .filter(player -> !player.isFinished())
                .map(Person::getName)
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }

    public Person findPlayerBy(final String name) {
        return players.get(name);
    }

    public GivenCards getCards(final String name) {
        Person player = findPlayerBy(name);
        return player.getCards();
    }

    public List<Integer> getPlayerScores() {
        return players.values()
                .stream()
                .map(Person::sum)
                .collect(Collectors.toList());
    }

    public List<Integer> getResults(GivenCards dealerCards) {
        return players.values()
                .stream()
                .map(player -> player.compare(dealerCards))
                .collect(Collectors.toList());
    }
}
