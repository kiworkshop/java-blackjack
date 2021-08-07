package blackjack.controller;

import blackjack.domain.GameSystem;
import blackjack.domain.card.Deck;
import blackjack.domain.dto.CardsResponse;
import blackjack.domain.dto.DealerAndPlayerCardsResponse;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Person;
import blackjack.domain.participant.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;

public class BlackjackController {
    private final static String DEFAULT_DEALER_NAME = "딜러";

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
        Deck deck = new Deck();
        Person dealer = new Dealer(DEFAULT_DEALER_NAME, deck.getTwoCards());
        Players players = new Players(playerNames, deck);
        GameSystem gameSystem = new GameSystem(dealer, players, deck);
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
        outputView.printResults(gameSystem.getPlayerNames(), gameSystem.getResults());
    }
}
