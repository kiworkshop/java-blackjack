package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.dto.DealerDto;
import blackjack.dto.ParticipantDto;
import blackjack.dto.PlayerDto;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private OutputView() {
    }

    public static void initialDeal(ParticipantDto participants) {
        printDealMessage(participants);
        printDealerHands(participants.getDealerDto());
        participants.getPlayers()
                .forEach(OutputView::printPlayerHands);
        System.out.println();
    }

    private static void printDealMessage(ParticipantDto participants) {
        List<String> names = participants.getPlayers().stream()
                .map(PlayerDto::getName)
                .collect(Collectors.toList());
        System.out.printf("%n딜러와 %s에게 2장의 카드를 나누었습니다.", String.join(", ", names));
    }

    public static void printDealerHands(DealerDto dto) {
        System.out.printf("%n%s: %s%n", dto.getName(), printCards(dto.getCards()));
    }

    public static void printPlayerHands(PlayerDto dto) {
        System.out.printf("%s: %s%n", dto.getName(), printCards(dto.getCards()));
    }

    private static String printCards(List<Card> cards) {
        List<String> cardSignatures = cards.stream()
                .map(card -> card.getSignature() + card.getSuit().getName())
                .collect(Collectors.toList());
        return String.join(", ", cardSignatures);
    }
}
