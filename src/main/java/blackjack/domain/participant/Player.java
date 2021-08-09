package blackjack.domain.participant;

import blackjack.domain.result.Rule;
import blackjack.domain.result.WinningResult;
import lombok.Getter;

import java.util.Arrays;

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

    public WinningResult resultVersusDealer(Dealer dealer) {
        return Arrays.stream(Rule.values())
                .filter(rule -> rule.compare(this, dealer))
                .findFirst()
                .get()
                .getWinningResult();
    }
}
