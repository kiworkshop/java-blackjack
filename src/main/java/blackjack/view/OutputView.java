package blackjack.view;

import blackjack.domain.dto.CardResponse;
import blackjack.domain.dto.CardsResponse;
import blackjack.domain.dto.DealerAndPlayerCardsResponse;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class OutputView {
    private static final String SEPARATOR = ", ";
    private static final String DECLINE_ANSWER = "n";
    private static final int FIRST_TWO_CARDS = 2;

    public void printGameStart(List<String> playerNames) {
        String names = String.join(SEPARATOR, playerNames);
        System.out.printf("%n딜러와 %s에게 2장의 카드를 나누었습니다.%n", names);
    }

    public void printFirstTwoCards(List<String> playerNames, DealerAndPlayerCardsResponse dealerAndPlayerCardsResponse) {
        System.out.printf("딜러: %s%n", combine(dealerAndPlayerCardsResponse.getDealerCards().getCards().get(0)));

        IntStream.range(0, playerNames.size())
                .forEach(i -> System.out.printf("%s카드: %s%n", playerNames.get(i), join(dealerAndPlayerCardsResponse.getAllPlayerCards().get(i))));
        System.out.println();
    }

    private String combine(final CardResponse cardResponse) {
        return cardResponse.getDenomination() + cardResponse.getSuit();
    }

    private String join(final CardsResponse cardResponses) {
        List<String> fullCardNames = cardResponses.getCards().stream()
                .map(this::combine)
                .collect(Collectors.toList());

        return String.join(SEPARATOR, fullCardNames);
    }

    public void printPlayerCards(final String playerName, final CardsResponse cardsResponse, String answer) {
        if (answer.equals(DECLINE_ANSWER) && cardsResponse.getCards().size() != FIRST_TWO_CARDS) {
            return;
        }

        System.out.printf("%s카드: %s%n", playerName, join(cardsResponse));
    }

    public void printDealerGetAnotherCard() {
        System.out.printf("%n딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }
}
