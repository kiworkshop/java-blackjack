package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final String SPLIT_DELIMITER = ",";
    private static final Scanner scanner = new Scanner(System.in);

    public List<String> getPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String playerNames = scanner.nextLine();
        checkBlank(playerNames);
        return splitByComma(playerNames);
    }

    private void checkBlank(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("한 글자 이상의 사람 이름을 입력해주세요");
        }
    }

    private List<String> splitByComma(String playerNames) {
        return Arrays.asList(playerNames.split(SPLIT_DELIMITER));
    }
}
