package blackjack.controller.dto;

import blackjack.domain.card.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DealerAndPlayerCardsResponse {
    private final List<Card> dealerCards;
    private final List<List<Card>> playerCards;

    public DealerAndPlayerCardsResponse(final List<Card> dealerCards, final List<List<Card>> playerCards) {
        this.dealerCards = dealerCards;
        this.playerCards = new ArrayList<>(playerCards);
    }

    public CardsResponse getDealerCards() {
        return new CardsResponse(dealerCards);
    }

    public List<CardsResponse> getAllPlayerCards() {
        return playerCards.stream()
                .map(CardsResponse::new)
                .collect(Collectors.toList());
    }
}
