package blackjack.view;

import blackjack.domain.Dealer;
import blackjack.domain.Human;
import blackjack.domain.Player;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    public static final String COMMA_DELIMITER = ", ";

    public static void printFirstTwoCards(Dealer dealer, List<Player> players) {
        List<String> playerNames = players.stream().map(player -> player.getName()).collect(Collectors.toList());
        System.out.printf("딜러와 %s에게 2장의 카드를 나누었습니다.\n", String.join(COMMA_DELIMITER, playerNames));
        System.out.printf("딜러: %s\n", dealer.getCards().get(0).getName());
        players.forEach(player -> {
            List<String> cardNames = player.getCards().stream().map(card -> card.getName()).collect(Collectors.toList());
            System.out.printf("%s: %s\n", player.getName(), String.join(COMMA_DELIMITER, cardNames));
        });
    }

    public static void printPlayersCards(Player player) {
        List<String> cardNames = player.getCards().stream().map(card -> card.getName()).collect(Collectors.toList());
        System.out.printf("%s: %s\n", player.getName(), String.join(COMMA_DELIMITER, cardNames));
    }
}
