package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    public static final String SEPARATOR = ",";

    public List<String> getPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String names = scanner.nextLine();
        return Arrays.stream(names.split(SEPARATOR))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public String getAnswerForAnotherCard(String player) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%n", player);
        return scanner.nextLine().trim();
    }
}
