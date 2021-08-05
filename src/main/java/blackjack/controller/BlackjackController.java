package blackjack.controller;

import blackjack.domain.game.DeckGenerator;
import blackjack.domain.game.RandomDeckGenerator;
import blackjack.domain.participant.Player;
import blackjack.domain.prize.PrizeResults;
import blackjack.dto.ParticipantsDto;
import blackjack.dto.PlayerDto;
import blackjack.dto.PlayerInput;
import blackjack.service.BlackjackService;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;

import static blackjack.exception.ExceptionMessage.INVALID_BET_AMOUNT_MESSAGE;

public class BlackjackController {

    private BlackjackService blackjackService;

    public void run() {
        try {
            setUp();
            initialDeal();
            deal();
            gameResult();
        } catch (NumberFormatException e) {
            OutputView.printError(INVALID_BET_AMOUNT_MESSAGE);
        } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
            OutputView.printError(e.getMessage());
        }
    }

    private void setUp() {
        List<PlayerInput> playerInputs = InputView.getPlayersInput();
        DeckGenerator deckGenerator = new RandomDeckGenerator();
        blackjackService = new BlackjackService(playerInputs, deckGenerator);
    }

    private void initialDeal() {
        ParticipantsDto initialParticipants = blackjackService.getParticipants();
        OutputView.printInitialDeal(initialParticipants);
    }

    private void deal() {
        blackjackService.getPlayers().forEach(this::hitOrStand);
        blackjackService.dealDealer();
    }

    private void hitOrStand(Player player) {
        while (InputView.hit(player.getName())) {
            PlayerDto playerDto = blackjackService.hit(player);
            OutputView.printPlayerHandsMessage(playerDto);
        }

        if (player.neverHit()) {
            OutputView.printPlayerHandsMessage(new PlayerDto(player));
        }
    }

    private void gameResult() {
        ParticipantsDto finalParticipants = blackjackService.getFinalParticipants();
        OutputView.printFinalHands(finalParticipants);

        PrizeResults prizeResults = blackjackService.calculatePrizeResults();
        OutputView.printPrizeResults(prizeResults);
    }
}