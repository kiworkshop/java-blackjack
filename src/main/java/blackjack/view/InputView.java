package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final String DELIMITER = ",";
    private static final List<String> VALID_HIT_OR_STAND_INPUTS = Arrays.asList("y", "n");

    private InputView() {
    }

    public static List<String> getPlayers() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");

        String input = getUserInput();
        validatePlayers(input);

        return Arrays.stream(input.split(DELIMITER))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    private static void validatePlayers(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("게임에 참여할 사람의 이름을 다시 확인해 주십시오.");
        }
    }

    public static boolean isHitOrStand() {
        System.out.println("pobi는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        String input = getUserInput();
        validateHitOrStand(input);

        return input.equals("y");
    }

    private static void validateHitOrStand(String input) {
        if (!VALID_HIT_OR_STAND_INPUTS.contains(input)) {
            throw new IllegalArgumentException("예는 y, 아니오는 n 을 입력해 주세요.");
        }
    }

    private static String getUserInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

}
