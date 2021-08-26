package blackjack.dto;

import blackjack.domain.card.Card;
import blackjack.domain.game.Hands;
import blackjack.domain.participant.Dealer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DealerDto {
    private static final String DEALER_NAME = "딜러";

    private final String name;
    private final List<Card> cards;
    private final int rankSum;

    public DealerDto(Dealer dealer) {
        this.name = DEALER_NAME;
        this.cards = Collections.unmodifiableList(new ArrayList<>(dealer.cards()));
        this.rankSum = dealer.hands().sumRank();
    }

    public DealerDto(Card card) {
        this(new Dealer(new Hands(Collections.singletonList(card))));
    }

    public Card faceUpCard() {
        return cards.get(0);
    }

    public String name() {
        return name;
    }

    public List<Card> cards() {
        return cards;
    }

    public int rankSum() {
        return rankSum;
    }
}
