package blackjack.dto;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Dealer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DealerDto {
    private static final String DEALER_NAME = "딜러";

    private final String name;
    private final List<Card> cards;
    private int rankSum;

    public DealerDto(List<Card> cards) {
        this.name = DEALER_NAME;
        this.cards = Collections.unmodifiableList(new ArrayList<>(cards));
        this.rankSum = cards.stream().mapToInt(Card::getRank).sum();
    }

    public DealerDto(Dealer dealer) {
        this(dealer.getCards());
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards;
    }

    public int getRankSum() {
        return rankSum;
    }
}
