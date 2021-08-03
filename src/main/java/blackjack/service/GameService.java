package blackjack.service;

import blackjack.domain.CardDeck;
import blackjack.domain.Dealer;
import blackjack.domain.Human;
import blackjack.domain.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameService {
    private List<Player> players = new ArrayList<>();
    private Dealer dealer = new Dealer();
    private static CardDeck cards = new CardDeck();
    public GameService() {

    }
    public GameService(List<Player> players, CardDeck cards) {
        this.players = players;
        this.cards = cards;
    }

    public List<Player> registPlayer(List<String> playerNames){
        players = new ArrayList<>();
        playerNames.forEach(playerName -> {
            players.add(new Player(playerName));
        });
        return players;
    }

    public static Human setFirstTwoCards(Human human){
        human.addCard(cards.getAdditionalCard());
        human.addCard(cards.getAdditionalCard());
        return human;
        //players.forEach(player -> player.addCard(cards.getAdditionalCard()));
    }
    public static Human setAdditionalCard(Human human){
        human.addCard(cards.getAdditionalCard());
        return human;
    }
}
