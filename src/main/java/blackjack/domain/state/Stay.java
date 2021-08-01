package blackjack.domain.state;

import blackjack.domain.GivenCards;
import blackjack.domain.enums.Result;

public class Stay extends Finished {

    public Stay(final GivenCards givenCards) {
        super(givenCards);
    }

    @Override
    public int result(final GivenCards competitorCards) {
        GivenCards myCards = getCards();

        if (myCards.isGreaterThan(competitorCards)) {
            return Result.WIN.getPoint();
        }

        if (myCards.isLessThan(competitorCards)) {
            return Result.LOSE.getPoint();
        }

        return Result.DRAW.getPoint();
    }
}
