package blackjack.domain.dto;

import blackjack.domain.card.Card;

import java.util.List;
import java.util.stream.Collectors;

public class CardsResponse {
    private final List<Card> cards;

    public CardsResponse(final List<Card> cards) {
        this.cards = cards;
    }

    public List<CardResponse> getCards() {
        return cards.stream()
                .map(card -> new CardResponse(card.getDenomination(), card.getSuit()))
                .collect(Collectors.toList());
    }
}
