package blackjack.domain.card;

public class AceCard extends Card {

    public static final int HARD_HAND = 1;
    public static final int SOFT_HAND = 11;
    public static final int ACE_CARD_DEFAULT_RANK = HARD_HAND;
    public static final String SIGNATURE = "A";

    public AceCard(Suit suit) {
        super(suit, ACE_CARD_DEFAULT_RANK, SIGNATURE);
    }

    public static int softOrHardSum(int sumExceptAceCards, int aceCardCount) {
        if (softAvailable(sumExceptAceCards, aceCardCount)) {
            return softSum(sumExceptAceCards, aceCardCount);
        }
        return hardSum(sumExceptAceCards, aceCardCount);
    }

    private static boolean softAvailable(int sumExceptAceCards, int aceCardCount) {
        int threshold = AceCard.SOFT_HAND - aceCardCount;
        return sumExceptAceCards <= threshold;
    }

    private static int softSum(int sumExceptAceCards, int aceCardCount) {
        int hardHandCount = aceCardCount - 1;
        return sumExceptAceCards + (hardHandCount * AceCard.HARD_HAND + AceCard.SOFT_HAND);
    }

    private static int hardSum(int sumExceptAceCards, int aceCardCount) {
        return sumExceptAceCards + (aceCardCount * AceCard.HARD_HAND);
    }

}
