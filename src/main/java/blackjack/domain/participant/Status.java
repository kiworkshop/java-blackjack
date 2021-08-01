package blackjack.domain.participant;

import static blackjack.domain.prize.PrizeResults.BLACKJACK_RANK;

public enum Status {
    BUST,
    BLACKJACK,
    HOLD;

    public static Status of(Participant participant) {
        if (participant.sumRank() > BLACKJACK_RANK) {
            return BUST;
        }

        if (participant.countAceCards() == 1 && participant.countMajorCards() == 1) {
            return BLACKJACK;
        }

        return HOLD;
    }
}
