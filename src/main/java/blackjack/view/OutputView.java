package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.dto.DealerDto;
import blackjack.dto.ParticipantDto;
import blackjack.dto.PlayerDto;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private static final int MAX_DEALER_CARDS_COUNT = 3;
    private static final String PARTICIPANTS_HANDS = "%s 카드: %s";
    private static final String PARTICIPANTS_FINAL_HANDS = "%n%s - 결과: %d";

    private OutputView() {
    }

    public static void printInitialDeal(ParticipantDto participants) {
        printDealMessage(participants);

        System.out.println(generateDealerHandsMessage(participants.getDealerDto()));
        participants.getPlayers()
                .forEach(player -> System.out.println(generatePlayerHandsMessage(player)));
        System.out.println();
    }

    private static void printDealMessage(ParticipantDto participants) {
        List<String> names = participants.getPlayers().stream()
                .map(PlayerDto::getName)
                .collect(Collectors.toList());
        System.out.printf("%n딜러와 %s에게 2장의 카드를 나누었습니다.%n", String.join(", ", names));
    }

    private static String generateDealerHandsMessage(DealerDto dto) {
        return String.format(PARTICIPANTS_HANDS, dto.getName(), printCards(dto.getCards()));
    }

    private static String generatePlayerHandsMessage(PlayerDto dto) {
        return String.format(PARTICIPANTS_HANDS, dto.getName(), printCards(dto.getCards()));
    }

    public static void printPlayerHandsMessage(PlayerDto dto) {
        System.out.print(generatePlayerHandsMessage(dto));
    }

    private static String printCards(List<Card> cards) {
        List<String> cardSignatures = cards.stream()
                .map(card -> card.getSignature() + card.getSuit().getName())
                .collect(Collectors.toList());
        return String.join(", ", cardSignatures);
    }

    public static void printFinalHands(ParticipantDto participants) {
        System.out.println();
        printFinalDealMessage(participants.getDealerDto());
        printFinalDealerHands(participants.getDealerDto());
        printFinalPlayersHands(participants.getPlayers());
    }

    private static void printFinalDealMessage(DealerDto dealer) {
        if (dealer.getCards().size() == MAX_DEALER_CARDS_COUNT) {
            System.out.println("딜러는 16 이하라 한 장의 카드를 더 받았습니다.");
        }
    }

    private static void printFinalDealerHands(DealerDto dealerDto) {
        System.out.printf(PARTICIPANTS_FINAL_HANDS,
                generateDealerHandsMessage(dealerDto),
                dealerDto.getRankSum());
    }

    private static void printFinalPlayersHands(List<PlayerDto> players) {
        players.forEach(player ->
                System.out.printf(PARTICIPANTS_FINAL_HANDS,
                        generatePlayerHandsMessage(player),
                        player.getRankSum()));
    }
}
