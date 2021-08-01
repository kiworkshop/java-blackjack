package blackjack.domain.state;

import blackjack.domain.GivenCards;
import blackjack.domain.enums.Result;

public class Blackjack extends Finished {

    public Blackjack(final GivenCards givenCards) {
        super(givenCards);
    }

    @Override
    public int result(final GivenCards competitorCards) {
        return Result.WIN.getPoint();
    }
}
