package blackjack.view;

import blackjack.domain.*;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    public static final String COMMA_DELIMITER = ", ";

    public static void printFirstTwoCards(Dealer dealer, List<Player> players) {
        List<String> playerNames = players.stream().map(player -> player.getName()).collect(Collectors.toList());
        System.out.printf("딜러와 %s에게 2장의 카드를 나누었습니다.\n", String.join(COMMA_DELIMITER, playerNames));
        System.out.printf("딜러: %s\n", dealer.getCards().get(0).getName());
        players.forEach(OutputView::printPlayersCards);
        System.out.println();
    }

    public static void printDealerAdditionCard() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.\n");
    }

    public static void printCardResult(Human human, int result) {
        List<String> cardNames = human.getCards().stream().map(card -> card.getName()).collect(Collectors.toList());
        System.out.printf("%s카드: %s - 결과: %d\n", human.getName(), String.join(COMMA_DELIMITER, cardNames), result);
    }

    public static void printTotalResult(GameTotalReuslt gameTotalReuslt) {
        System.out.println();
        System.out.println("## 최종 승패");
        System.out.printf("딜러: %d승 %d패\n", gameTotalReuslt.getDealerWinCount(), gameTotalReuslt.getDealerLoseCount());
        gameTotalReuslt.getPlayerResults().forEach(OutputView::printPlayersResult);
    }

    public static void printPlayersCards(Player player) {
        List<String> cardNames = player.getCards().stream().map(card -> card.getName()).collect(Collectors.toList());
        System.out.printf("%s카드: %s\n", player.getName(), String.join(COMMA_DELIMITER, cardNames));
    }

    private static void printPlayersResult(GamePlayerResult gamePlayerResult) {
        System.out.printf("%s: %s\n", gamePlayerResult.getName(), gamePlayerResult.getResult());
    }
}
