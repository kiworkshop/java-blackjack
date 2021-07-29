package blackjack.view;

import blackjack.domain.participant.Player;
import blackjack.dto.PlayersNameInputDTO;

import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public PlayersNameInputDTO getPlayersName() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String input = scanner.nextLine();
        return new PlayersNameInputDTO(input);
    }

    public String getPlayersResponse(Player player) {
        StringBuilder sb = new StringBuilder();
        sb.append(player.getName())
                .append("는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        System.out.println(sb);
        return scanner.nextLine().trim();
    }
}
