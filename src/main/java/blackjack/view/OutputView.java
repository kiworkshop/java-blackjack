package blackjack.view;

import java.util.List;

public class OutputView {


    public void printGameStart(List<String> playerNames) {
        String names = String.join(", ", playerNames);
        System.out.printf("딜러와 %s에게 2장의 카드를 나누었습니다.", names);
    }
}
