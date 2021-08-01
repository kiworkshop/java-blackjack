package blackjack.domain.state.finished;

import blackjack.domain.card.GivenCards;
import blackjack.domain.enums.Result;

public class Stay extends Finished {

    public Stay(final GivenCards givenCards) {
        super(givenCards);
    }

    @Override
    public int result(final GivenCards competitorCards) {
        if (givenCards.isGreaterThan(competitorCards)) {
            return Result.WIN.getPoint();
        }

        if (givenCards.isLessThan(competitorCards)) {
            return Result.LOSE.getPoint();
        }

        return Result.DRAW.getPoint();
    }
}
