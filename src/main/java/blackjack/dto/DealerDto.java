package blackjack.dto;

import blackjack.domain.card.Card;
import blackjack.domain.game.Hands;
import blackjack.domain.gamer.Dealer;

import java.util.List;

public class DealerDto {
    private final String name;
    private final Hands hands;

    public DealerDto(Dealer dealer) {
        this.name = dealer.name();
        this.hands = dealer.hands();
    }

    public Card faceUpCard() {
        return hands.cards().get(0);
    }

    public int rankSum() {
        return hands.sumRank();
    }

    public String name() {
        return name;
    }

    public List<Card> cards() {
        return hands.cards();
    }
}
