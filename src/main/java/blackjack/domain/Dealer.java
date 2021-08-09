package blackjack.domain;

public class Dealer extends Participant {
    public static final int DEALER_MIN_SCORE = 16;

    public Dealer() {
        super("dealer");
    }

    public boolean isUnder16() {
        Cards cards = new Cards(super.getCards());
        return cards.getCardScore() <= DEALER_MIN_SCORE;
    }
}
