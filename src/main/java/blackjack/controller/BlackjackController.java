package blackjack.controller;

import blackjack.domain.GameSystem;
import blackjack.domain.dto.CardsResponse;
import blackjack.domain.dto.DealerAndPlayerCardsResponse;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;

public class BlackjackController {
    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        GameSystem gameSystem = setup(inputView.getPlayerNames());
        playerMode(gameSystem);
        dealerMode(gameSystem);
        printResult(gameSystem);
    }

    private GameSystem setup(final List<String> playerNames) {
        GameSystem gameSystem = GameSystem.create(playerNames);
        outputView.printGameStart(gameSystem.getPlayerNames());
        DealerAndPlayerCardsResponse dealerAndPlayerCardsResponse = new DealerAndPlayerCardsResponse(gameSystem.getDealerCards(), gameSystem.getPlayerCards());
        outputView.printFirstTwoCards(gameSystem.getPlayerNames(), dealerAndPlayerCardsResponse);
        return gameSystem;
    }

    private void playerMode(final GameSystem gameSystem) {
        while (!gameSystem.allPlayersFinished()) {
            String currentPlayer = gameSystem.getCurrentPlayer();
            String answer = inputView.getAnswerForAnotherCard(currentPlayer);
            gameSystem.hit(answer, currentPlayer);
            CardsResponse cardsResponse = new CardsResponse(gameSystem.getCards(currentPlayer));
            outputView.printPlayerCards(currentPlayer, cardsResponse, answer);
        }
    }

    private void dealerMode(final GameSystem gameSystem) {
        while (!gameSystem.isDealerFinished()) {
            outputView.printDealerGetAnotherCard();
            gameSystem.hit();
        }
    }

    private void printResult(final GameSystem gameSystem) {
        DealerAndPlayerCardsResponse dealerAndPlayerCardsResponse = new DealerAndPlayerCardsResponse(gameSystem.getDealerCards(), gameSystem.getPlayerCards());
        outputView.printCardsAndScores(gameSystem.getPlayerNames(), dealerAndPlayerCardsResponse, gameSystem.getDealerScore(), gameSystem.getPlayerScores());
    }
}
