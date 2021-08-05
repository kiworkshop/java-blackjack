package blackjack.view;

import blackjack.dto.PlayerInput;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static blackjack.exception.ExceptionMessage.INVALID_HIT_OR_STAND_MESSAGE;
import static blackjack.exception.ExceptionMessage.INVALID_PLAYER_NAME_MESSAGE;

public class InputView {

    private static final String DELIMITER = ",";
    private static final List<String> VALID_HIT_OR_STAND_INPUTS = Arrays.asList("y", "n");

    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static List<PlayerInput> getPlayersInput() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String input = scanner.nextLine();
        validateInputNullOrEmpty(input);

        List<String> playerNames = parsePlayerNames(input);
        validatePlayersSize(playerNames);

        List<PlayerInput> playerInputs = new ArrayList<>();
        playerNames.forEach(playerName -> {
            int betAmount = getBetAmountInput(playerName);
            playerInputs.add(new PlayerInput(playerName, betAmount));
        });

        return playerInputs;
    }

    private static void validateInputNullOrEmpty(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException(INVALID_PLAYER_NAME_MESSAGE);
        }
    }

    private static List<String> parsePlayerNames(String input) {
        return Arrays.stream(input.split(DELIMITER))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    private static void validatePlayersSize(List<String> playerNames) {
        if (playerNames.isEmpty()) {
            throw new IllegalArgumentException(INVALID_PLAYER_NAME_MESSAGE);
        }
    }

    private static int getBetAmountInput(String playerName) {
        System.out.printf("%s의 배팅 금액은?%n", playerName);
        String input = scanner.nextLine();
        validateInputNullOrEmpty(input);

        return Integer.parseInt(input);
    }

    public static boolean hit(String playerName) {
        System.out.printf("%n%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%n", playerName);
        String input = scanner.nextLine();
        validateHit(input);

        return input.equals("y");
    }

    private static void validateHit(String input) {
        if (!VALID_HIT_OR_STAND_INPUTS.contains(input)) {
            throw new IllegalArgumentException(INVALID_HIT_OR_STAND_MESSAGE);
        }
    }
}
