package blackjack.card;

public class AceCard extends Card {

    static final int ACE_CARD_DEFAULT_RANK = 1;
    private static final String SIGNATURE = "A";

    public AceCard(Suit suit) {
        super(suit, ACE_CARD_DEFAULT_RANK, SIGNATURE);
    }
}
