package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.prize.DealerPrize;
import blackjack.domain.prize.PlayersPrize;
import blackjack.domain.prize.PrizeResults;
import blackjack.dto.DealerDto;
import blackjack.dto.GamerDto;
import blackjack.dto.PlayerDto;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private static final int MAX_DEALER_CARD_COUNT = 3;
    private static final String GAMER_HANDS = "%s 카드: %s";
    private static final String GAMER_FINAL_HANDS = "%n%s - 결과: %d";

    private OutputView() {
    }

    public static void printInitialDeal(GamerDto gamerDto) {
        printDealMessage(gamerDto);

        System.out.println(generateDealerHandsMessage(gamerDto.dealerDto()));
        gamerDto.playersDto()
                .forEach(player -> System.out.println(generatePlayerHandsMessage(player)));
    }

    private static void printDealMessage(GamerDto gamerDto) {
        List<String> names = gamerDto.playersDto().stream()
                .map(PlayerDto::name)
                .collect(Collectors.toList());
        System.out.printf("%n딜러와 %s에게 2장의 카드를 나누었습니다.%n", String.join(", ", names));
    }

    public static void printPlayerHandsMessage(PlayerDto dto) {
        System.out.print(generatePlayerHandsMessage(dto));
    }

    private static String printCards(List<Card> cards) {
        List<String> cardSignatures = cards.stream()
                .map(Card::key)
                .collect(Collectors.toList());
        return String.join(", ", cardSignatures);
    }

    public static void printFinalHands(GamerDto gamerDto) {
        System.out.println();
        printFinalDealMessage(gamerDto.dealerDto());

        printFinalDealerHands(gamerDto.dealerDto());
        printFinalPlayersHands(gamerDto.playersDto());
        System.out.println();
    }

    private static void printFinalDealMessage(DealerDto dealer) {
        if (dealer.cards().size() == MAX_DEALER_CARD_COUNT) {
            System.out.println("딜러는 16 이하라 한 장의 카드를 더 받았습니다.");
        }
    }

    private static void printFinalDealerHands(DealerDto dealerDto) {
        System.out.printf(GAMER_FINAL_HANDS,
                generateFinalDealerHandsMessage(dealerDto),
                dealerDto.rankSum());
    }

    private static void printFinalPlayersHands(List<PlayerDto> players) {
        players.forEach(player ->
                System.out.printf(GAMER_FINAL_HANDS,
                        generatePlayerHandsMessage(player),
                        player.rankSum()));
    }

    private static String generateDealerHandsMessage(DealerDto dto) {
        return String.format(GAMER_HANDS, dto.name(), dto.faceUpCard().key());
    }

    private static String generateFinalDealerHandsMessage(DealerDto dto) {
        return String.format(GAMER_HANDS, dto.name(), printCards(dto.cards()));
    }

    private static String generatePlayerHandsMessage(PlayerDto dto) {
        return String.format(GAMER_HANDS, dto.name(), printCards(dto.cards()));
    }

    public static void printPrizeResults(PrizeResults prizeResults) {
        System.out.println();
        System.out.println("## 최종 승패");
        printDealerPrize(prizeResults.dealerPrize());
        printPlayersPrize(prizeResults.playersPrize());
    }

    private static void printDealerPrize(DealerPrize dealerPrize) {
        System.out.printf("딜러: %d승 %d패 %d무%n", dealerPrize.getWinCount(), dealerPrize.getLoseCount(), dealerPrize.getTieCount());
    }

    private static void printPlayersPrize(PlayersPrize playersPrize) {
        playersPrize.values().forEach(playerPrize -> System.out.printf("%s: %s%n", playerPrize.name(), playerPrize.value().getTitle()));
    }
}