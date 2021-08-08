package blackjack.controller.dto;

public class CardResponse {
    private final String denomination;
    private final String suit;

    public CardResponse(final String denomination, final String suit) {
        this.denomination = denomination;
        this.suit = suit;
    }

    public String getDenomination() {
        return denomination;
    }

    public String getSuit() {
        return suit;
    }
}
