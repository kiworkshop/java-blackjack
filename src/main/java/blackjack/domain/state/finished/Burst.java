package blackjack.domain.state.finished;

import blackjack.domain.card.GivenCards;
import blackjack.domain.enums.Result;

public class Burst extends Finished {

    public Burst(final GivenCards givenCards) {
        super(givenCards);
    }

    @Override
    public int compare(final GivenCards competitorCards) {
        return Result.LOSE.getPoint();
    }
}
