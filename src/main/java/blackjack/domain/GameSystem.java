package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.GivenCards;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Person;
import blackjack.domain.participant.Player;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class GameSystem {
    public static final int BLACKJACK = 21;
    private static final String DEFAULT_DEALER_NAME = "딜러";
    private static final String ACCEPT_ANSWER = "y";
    private static final String DECLINE_ANSWER = "n";

    private final Person dealer;
    private final List<Person> players;

    protected GameSystem(final Person dealer, final List<Person> players) {
        this.dealer = dealer;
        this.players = players;
    }

    public static GameSystem create(final List<String> playerNames) {
        return new GameSystem(new Dealer(DEFAULT_DEALER_NAME, Deck.getTwoCards()), playerNames.stream()
                .map(name -> new Player(name, Deck.getTwoCards()))
                .collect(Collectors.toList()));
    }

    public List<String> getPlayerNames() {
        return Collections.unmodifiableList(players.stream()
                .map(Person::getName)
                .collect(Collectors.toList()));
    }

    public List<Card> getDealerCards() {
        return Collections.unmodifiableList(dealer.getCards().list());
    }

    public List<List<Card>> getPlayerCards() {
        return Collections.unmodifiableList(players.stream()
                .map(Person::getCards)
                .map(GivenCards::list)
                .collect(Collectors.toList()));
    }

    public boolean allPlayersFinished() {
        return players.stream()
                .allMatch(Person::isFinished);
    }

    public String getCurrentPlayer() {
        return players.stream()
                .filter(player -> !player.isFinished())
                .map(Person::getName)
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }

    public void hit(final String answer, final String name) {
        validate(answer);
        Person player = findPlayerBy(name);

        if (answer.equals(DECLINE_ANSWER)) {
            player.stay();
            return;
        }

        player.hit(Deck.getCard());
    }

    private void validate(final String answer) {
        if (!answer.equals(ACCEPT_ANSWER) && !answer.equals(DECLINE_ANSWER)) {
            throw new IllegalArgumentException("y 또는 n을 입력해주세요.");
        }
    }

    private Person findPlayerBy(final String name) {
        return players.stream()
                .filter(player -> player.getName().equals(name))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public void hit() {
        dealer.hit(Deck.getCard());
    }

    public boolean isDealerFinished() {
        return dealer.isFinished();
    }

    public List<Card> getCards(final String name) {
        Person player = findPlayerBy(name);
        return player.getCards().list();
    }

    public int getDealerScore() {
        return dealer.sum();
    }

    public List<Integer> getPlayerScores() {
        return players.stream()
                .map(Person::sum)
                .collect(Collectors.toList());
    }
}
