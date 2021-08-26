package blackjack.controller;

import blackjack.domain.participant.Player;
import blackjack.domain.prize.PrizeResults;
import blackjack.dto.ParticipantsDto;
import blackjack.dto.PlayerDto;
import blackjack.service.BlackjackService;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;

public class BlackjackController {

    public void run() {
        List<String> players = InputView.getPlayers();
        BlackjackService blackjackService = new BlackjackService(players);

        ParticipantsDto initialParticipants = blackjackService.getParticipants();
        OutputView.printInitialDeal(initialParticipants);

        deal(blackjackService);
        ParticipantsDto finalParticipants = blackjackService.getParticipants();
        OutputView.printFinalHands(finalParticipants);

        PrizeResults prizeResults = blackjackService.calculatePrizeResults();
        OutputView.printPrizeResults(prizeResults);
    }

    private void deal(BlackjackService blackjackService) {
        dealEachPlayer(blackjackService);
        blackjackService.dealDealer();
    }

    private void dealEachPlayer(BlackjackService blackjackService) {
        blackjackService.getPlayers()
                .forEach(player -> hitOrStand(blackjackService, player));
    }

    private void hitOrStand(BlackjackService blackjackService, Player player) {
        while (InputView.hit(player.getName())) {
            PlayerDto playerDto = blackjackService.hit(player);
            OutputView.printPlayerHandsMessage(playerDto);
        }

        if (player.neverHit()) {
            OutputView.printPlayerHandsMessage(new PlayerDto(player));
        }
    }
}