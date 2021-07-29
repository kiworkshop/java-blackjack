package blackjack.view;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.result.GameResult;
import blackjack.domain.result.WinningResult;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    private static final String AND = "와 ";
    private static final String TWO_CARDS_GIVEN = "에게 2장의 카드를 나누었습니다.";
    private static final String NEW_LINE = System.lineSeparator();
    private static final String DEALER_DRAW_CARD = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String RESULT = " - 결과: ";
    private static final String GAME_RESULT = "\n## 최종승패";
    private static final String DEALER = "딜러: ";
    private static final String BLANK = " ";
    private static final String COLON = ": ";

    public void printFirstCardsGiven(List<Player> players, Dealer dealer) {
        StringBuilder sb = new StringBuilder();
        sb.append(dealer.getName())
                .append(AND);
        sb.append(players.stream()
                .map(Player::getName)
                .collect(Collectors.joining(", ")));
        sb.append(TWO_CARDS_GIVEN);
        System.out.println(sb);

    }

    public void printDealerCard(Dealer dealer) {
        System.out.println(dealer);
    }

    public void printPlayersCard(List<Player> players) {
        for (Player player : players) {
            System.out.println(player);
        }
        System.out.println();
    }

    public void printCards(Player player) {
        System.out.println(player);
    }

    public void printDealerCardGiven() {
        System.out.println(DEALER_DRAW_CARD);
    }

    public void printCardsResult(Dealer dealer, List<Player> players) {
        printDealerCardsResult(dealer);

        for (Player player : players) {
            printPlayerCardsResult(player);
        }
    }

    private void printDealerCardsResult(Dealer dealer) {
        StringBuilder sb = new StringBuilder();
        sb.append(NEW_LINE)
                .append(dealer.toString())
                .append(RESULT)
                .append(dealer.getCardsSum());

        System.out.println(sb);
    }

    private void printPlayerCardsResult(Player player) {
        StringBuilder sb = new StringBuilder(player.toString());
        sb.append(RESULT);
        sb.append(player.getCardsSum());

        System.out.println(sb);
    }

    public void printGameResult(GameResult gameResult) {
        System.out.println(GAME_RESULT);
        printDealerResult(gameResult.getDealerResult());
        printPlayersResult(gameResult.getPlayersResult());

    }

    private void printDealerResult(Map<WinningResult, Integer> dealerResult) {
        StringBuilder sb = new StringBuilder(DEALER);
        sb.append(dealerResult.entrySet()
                .stream()
                .filter(winningResult -> winningResult.getValue() > 0)
                .map(winningResult -> winningResult.getValue() + winningResult.getKey().getResult())
                .collect(Collectors.joining(BLANK)));

        System.out.println(sb);

    }

    private void printPlayersResult(Map<Player, WinningResult> playersResult) {
        StringBuilder sb = new StringBuilder();
        sb.append(playersResult.entrySet()
                .stream()
                .map(playerResult -> playerResult.getKey().getName() + COLON + playerResult.getValue().getResult())
                .collect(Collectors.joining(NEW_LINE)));

        System.out.println(sb);
    }


}
