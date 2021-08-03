package blackjack.view;

import blackjack.domain.dto.CardResponse;
import blackjack.domain.dto.CardsResponse;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class OutputView {
    private static final String SEPARATOR = ", ";

    public void printGameStart(List<String> playerNames) {
        String names = String.join(SEPARATOR, playerNames);
        System.out.printf("딜러와 %s에게 2장의 카드를 나누었습니다.%n", names);
    }

    public void printFirstTwoCards(List<String> playerNames, CardsResponse cardsResponse) {
        System.out.printf("딜러: %s%n", combine(cardsResponse.getDealerCards().get(0)));

        IntStream.range(0, playerNames.size())
                .forEach(i -> System.out.printf("%s카드: %s%n", playerNames.get(i), playerCards(cardsResponse.getAllPlayerCards().get(i))));
    }

    private String combine(final CardResponse cardResponse) {
        return cardResponse.getDenomination() + cardResponse.getSuit();
    }

    private String playerCards(final List<CardResponse> cardResponses) {
        List<String> fullCardNames = cardResponses.stream()
                .map(this::combine)
                .collect(Collectors.toList());

        return String.join(SEPARATOR, fullCardNames);
    }

    public void printDealerGetAnotherCard() {
        System.out.printf("딜러는 16이하라 한장의 카드를 더 받았습니다.%n");
    }
}
