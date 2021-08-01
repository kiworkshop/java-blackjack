package blackjack.domain.state;

import blackjack.domain.GivenCards;
import blackjack.domain.enums.Result;

public class Burst extends Finished {

    public Burst(final GivenCards givenCards) {
        super(givenCards);
    }

    @Override
    public int result(final GivenCards competitorCards) {
        return Result.LOSE.getPoint();
    }
}
