package blackjack.controller;

import blackjack.domain.CardDeck;
import blackjack.domain.Dealer;
import blackjack.domain.Player;
import blackjack.service.GameService;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;
import java.util.stream.Collectors;

public class GameController {
    public static void main(String[] args) {
        GameService gameService = new GameService();
        CardDeck cards = new CardDeck();
        Dealer dealer = new Dealer();

        List<String> playersNames = InputView.getPlayerNames();
        List<Player> players = gameService.registPlayer(playersNames);

        dealer = (Dealer) gameService.setFirstTwoCards(dealer);
        players.stream().forEach(GameService::setFirstTwoCards);

        OutputView.printFirstTwoCards(dealer,players);

    }
}
