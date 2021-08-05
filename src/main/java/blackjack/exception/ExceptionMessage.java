package blackjack.exception;

import static blackjack.dto.PlayerInput.MINIMUM_BET_AMOUNT;

public class ExceptionMessage {
    public static final String INVALID_PLAYER_NAME_MESSAGE = "게임에 참여할 사람의 이름을 다시 확인해 주세요.";
    public static final String INVALID_HIT_OR_STAND_MESSAGE = "예는 y, 아니오는 n 을 입력해 주세요.";
    public static final String INVALID_BET_AMOUNT_MESSAGE = "베팅 금액은 " + MINIMUM_BET_AMOUNT + " 이상의 정수로 입력해 주세요.";
}
