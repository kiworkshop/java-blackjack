package blackjack.view;

import blackjack.domain.Player;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class InputView {
    private static final String SPLIT_DELIMITER = ",";
    private static final Scanner scanner = new Scanner(System.in);

    public static List<String> getPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String playerNames = scanner.nextLine();
        checkBlank(playerNames);
        return splitByComma(playerNames);
    }
    public static int getBettingMoney(String playerName) {
        System.out.printf("%s의 배팅 금액은?\n", playerName);
        String bettingAmount = scanner.nextLine();
        checkBlank(bettingAmount);
        return Integer.parseInt(bettingAmount);
    }

    public static String getAdditionalCard(Player player) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)\n", player.getName());
        String answer = scanner.nextLine();
        checkBlank(answer);
        return answer.toUpperCase(Locale.ROOT);
    }

    private static void checkBlank(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("한 글자 이상의 사람 이름을 입력해주세요");
        }
    }

    private static List<String> splitByComma(String playerNames) {
        return Arrays.asList(playerNames.split(SPLIT_DELIMITER));
    }
}
