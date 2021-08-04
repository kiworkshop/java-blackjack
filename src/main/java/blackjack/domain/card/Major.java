package blackjack.domain.card;

public enum Major {
    J("J"),
    Q("Q"),
    K("K");

    private final String signature;

    Major(String signature) {
        this.signature = signature;
    }
}
