package blackjack.service;

import blackjack.domain.*;
import blackjack.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class GameService {
    private List<Player> players = new ArrayList<>();
    //    private Dealer dealer = new Dealer();
    private static CardDeck cards = new CardDeck();
    static final int BLACKJACK = 21;
    static final int ACE_BONUS_SCORE = 10;

    public GameService() {

    }

    public GameService(List<Player> players, CardDeck cards) {
        this.players = players;
        this.cards = cards;
    }

    public List<Player> registPlayer(List<String> playerNames) {
        players = new ArrayList<>();
        playerNames.forEach(playerName -> {
            players.add(new Player(playerName));
        });
        return players;
    }

    public static Human setFirstTwoCards(Human human) {
        human.addCard(cards.getAdditionalCard());
        human.addCard(cards.getAdditionalCard());
        return human;
        //players.forEach(player -> player.addCard(cards.getAdditionalCard()));
    }

    public static Human setAdditionalCard(Human human) {
        human.addCard(cards.getAdditionalCard());
        return human;
    }

    public int getCardScore(Human human) {
        int sum = human.getCards().stream()
                .mapToInt(Card::getScore)
                .sum();

        if (hasAce(human.getCards()) && sum + ACE_BONUS_SCORE <= BLACKJACK) {
            sum += ACE_BONUS_SCORE;
        }

        return sum;
    }

    public boolean hasAce(List<Card> cards) {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    public boolean isBurst(int sum) {
        return sum > BLACKJACK;

    }

//    public GamePlayerResult getResult(Dealer dealer, List<Player> players) {
//
//        int dealerScore = getCardScore(dealer);
//        players.forEach(player -> {
//            new GamePlayerResult(player, dealerScore);
//        });
//        return
//    }

    public GameTotalReuslt getCameTotalResult(Dealer dealer, List<Player> players) {
        List<GamePlayerResult> playerResults = new ArrayList<>();
        int dealerScore = getCardScore(dealer);
        players.forEach(player -> {
            playerResults.add(new GamePlayerResult(player, dealerScore));
        });

        return new GameTotalReuslt(playerResults);
    }
}
