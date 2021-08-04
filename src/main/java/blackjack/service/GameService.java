package blackjack.service;

import blackjack.domain.*;

import java.util.ArrayList;
import java.util.List;

public class GameService {
    public static final int DEALER_MIN_SCORE = 16;
    static final int BLACKJACK = 21;
    static final int ACE_BONUS_SCORE = 10;

    private static CardDeck cards = new CardDeck();

    public List<Player> registPlayer(List<String> playerNames) {
        List<Player> players = new ArrayList<>();
        playerNames.forEach(playerName -> {
            players.add(new Player(playerName));
        });
        return players;
    }

    public static Human setFirstTwoCards(Human human) {
        human.addCard(cards.getAdditionalCard());
        human.addCard(cards.getAdditionalCard());
        return human;
    }

    public static Human hit(Human human) {
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

    private boolean hasAce(List<Card> cards) {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    public GameTotalReuslt getCameTotalResult(Dealer dealer, List<Player> players) {
        List<GamePlayerResult> playerResults = new ArrayList<>();
        int dealerScore = getCardScore(dealer);
        players.forEach(player -> {
            playerResults.add(new GamePlayerResult(player, dealerScore));
        });

        return new GameTotalReuslt(playerResults);
    }

    public boolean getMoreCard(Dealer dealer) {
        return getCardScore(dealer) <= DEALER_MIN_SCORE;
    }
}
