package blackjack.view;

import blackjack.domain.participant.Player;
import blackjack.dto.DrawCardResponseDTO;
import blackjack.dto.PlayersNameInputDTO;

import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String PLAYER_NAMES_INPUT_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String DRAW_CARD_RESPONSE_INPUT_MESSAGE = "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";

    public PlayersNameInputDTO getPlayersName() {
        System.out.println(PLAYER_NAMES_INPUT_MESSAGE);
        String input = scanner.nextLine();
        return new PlayersNameInputDTO(input);
    }

    public DrawCardResponseDTO getPlayersResponse(Player player) {
        StringBuilder sb = new StringBuilder();
        sb.append(player.getName())
                .append(DRAW_CARD_RESPONSE_INPUT_MESSAGE);
        System.out.println(sb);
        String response = scanner.nextLine();
        return new DrawCardResponseDTO(response);
    }
}
