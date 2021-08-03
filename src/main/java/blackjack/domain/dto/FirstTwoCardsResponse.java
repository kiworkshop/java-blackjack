package blackjack.domain.dto;

import blackjack.domain.card.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FirstTwoCardsResponse {
    private final List<Card> dealerCards;
    private final List<List<Card>> playerCards;

    public FirstTwoCardsResponse(final List<Card> dealerCards, final List<List<Card>> playerCards) {
        this.dealerCards = dealerCards;
        this.playerCards = new ArrayList<>(playerCards);
    }

    public List<CardResponse> getDealerCards() {
        return convertToCardResponse(dealerCards);
    }

    public List<List<CardResponse>> getAllPlayerCards() {
        return playerCards.stream()
                .map(FirstTwoCardsResponse::convertToCardResponse)
                .collect(Collectors.toList());
    }

    private static List<CardResponse> convertToCardResponse(final List<Card> givenCards) {
        return givenCards.stream()
                .map(card -> new CardResponse(card.getDenomination(), card.getSuit()))
                .collect(Collectors.toList());
    }
}
