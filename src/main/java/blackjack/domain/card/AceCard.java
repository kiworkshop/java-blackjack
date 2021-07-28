package blackjack.domain.card;

public class AceCard extends Card {

    private static final int HARD_HAND = 1;
    private static final int SOFT_HAND = 11;
    private static final int RANK_SUM_THRESHOLD = 10;
    public static final int ACE_CARD_DEFAULT_RANK = HARD_HAND;
    public static final String SIGNATURE = "A";

    public AceCard(Suit suit) {
        super(suit, ACE_CARD_DEFAULT_RANK, SIGNATURE);
    }

    public int getRank(int sumExceptAceCard) {
        if (sumExceptAceCard <= RANK_SUM_THRESHOLD) {
            return SOFT_HAND;
        }
        return HARD_HAND;
    }
}
