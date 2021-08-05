package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import blackjack.dto.PlayerInput;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Table {
    public static final int INITIAL_DEAL_COUNT = 2;

    private final Deck deck;
    private final Dealer dealer;
    private final List<Player> players;

    public Table(List<PlayerInput> playerInputs, DeckGenerator deckGenerator) {
        this.deck = new Deck(deckGenerator);
        this.dealer = new Dealer(deck.drawCards(INITIAL_DEAL_COUNT));
        this.players = Collections.unmodifiableList(generatePlayers(playerInputs));
    }

    private List<Player> generatePlayers(List<PlayerInput> playerInputs) {
        return playerInputs.stream()
                .map(playerInput -> new Player(playerInput, deck.drawCards(INITIAL_DEAL_COUNT)))
                .collect(Collectors.toList());
    }

    public void hit(Participant participant) {
        Card card = deck.drawCard();
        participant.take(card);
    }

    public void finalDeal() {
        while (dealer.hit()) {
            hit(dealer);
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
