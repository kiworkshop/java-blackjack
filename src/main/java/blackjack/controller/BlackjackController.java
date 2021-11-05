package blackjack.controller;

import blackjack.domain.gamer.Player;
import blackjack.domain.prize.PrizeResults;
import blackjack.dto.GamerDto;
import blackjack.dto.PlayerDto;
import blackjack.service.BlackjackService;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;

public class BlackjackController {

    public void run() {
        List<String> players = InputView.getPlayers();
        BlackjackService service = new BlackjackService(players);

        GamerDto initialGamer = service.generateGamer();
        OutputView.printInitialDeal(initialGamer);

        dealEachPlayer(service);
        service.dealDealer();

        GamerDto finalGamer = service.generateGamer();
        OutputView.printFinalHands(finalGamer);

        PrizeResults prizeResults = service.calculatePrizeResults();
        OutputView.printPrizeResults(prizeResults);
    }

    private void dealEachPlayer(BlackjackService service) {
        service.players()
                .forEach(player -> hitOrStand(service, player));
    }

    private void hitOrStand(BlackjackService service, Player player) {
        while (InputView.hit(player.name())) {
            PlayerDto playerDto = service.hit(player);
            OutputView.printPlayerHandsMessage(playerDto);
        }

        if (player.neverHit()) {
            OutputView.printPlayerHandsMessage(new PlayerDto(player));
        }
    }
}