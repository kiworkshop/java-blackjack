package blackjack.domain.participant;

import lombok.Getter;

@Getter
public class Player extends Participant {

    private static final int PLAYER_DRAW_THRESHOLD = 21;
    private static final String DEALER_NAME = "딜러";
    private static final String CHECK_SAME_NAME_AS_DEALER = "이름은 딜러와 같을 수 없으니 다른 이름을 지정해주세요.";

    public Player(String name) {
        super(name);
        validateDealerNameDuplicated(name);
    }

    private void validateDealerNameDuplicated(String name) {
        if (name.equals(DEALER_NAME)) {
            throw new IllegalArgumentException(CHECK_SAME_NAME_AS_DEALER);
        }
    }

    @Override
    public boolean drawable() {
        return getCardsSum() < PLAYER_DRAW_THRESHOLD;
    }
}
