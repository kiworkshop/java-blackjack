package blackjack.controller;

import blackjack.domain.GameSystem;
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
        GameSystem gameSystem = new GameSystem(playerNames);
        outputView.printGameStart(gameSystem.getPlayerNames());
    }
}
