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
        List<String> playerNames = inputView.getPlayerNames();
        GameSystem gameSystem = GameSystem.create(playerNames);
        outputView.printGameStart(gameSystem.getPlayerNames());
        DealerAndPlayerCardsResponse dealerAndPlayerCardsResponse = new DealerAndPlayerCardsResponse(gameSystem.getDealerCards(), gameSystem.getPlayerCards());
        outputView.printFirstTwoCards(gameSystem.getPlayerNames(), dealerAndPlayerCardsResponse);

        while (!gameSystem.allPlayersFinished()) {
            String currentPlayer = gameSystem.getCurrentPlayer();
            String answer = inputView.getAnswerForAnotherCard(currentPlayer);
            CardsResponse cardsResponse = new CardsResponse(gameSystem.getCards(currentPlayer));
            gameSystem.hit(answer, currentPlayer);
            outputView.printPlayerCards(currentPlayer, cardsResponse, answer);
        }

        while (!gameSystem.isDealerFinished()) {
            outputView.printDealerGetAnotherCard();
            gameSystem.hit();
        }
    }
}
