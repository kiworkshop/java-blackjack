package blackjack.controller;

import blackjack.domain.card.Deck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.PlayersFactory;
import blackjack.domain.result.GameResult;
import blackjack.dto.DrawCardRequestDto;
import blackjack.dto.PlayersNameInputDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;

public class BlackJackController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void run() {
        PlayersNameInputDto namesInput = inputView.getPlayersName();
        Dealer dealer = new Dealer();
        Deck deck = new Deck();
        List<Player> players = PlayersFactory.createPlayers(namesInput.getPlayersName());

        drawTowCards(dealer, players, deck);
        drawCardToPlayers(players, deck);
        drawCardToDealer(dealer, deck);
        outputView.printCardsResult(dealer, players);
        outputView.printGameResult(GameResult.of(dealer, players));
    }

    private void drawTowCards(Dealer dealer, List<Player> players, Deck deck) {
        for (Player player : players) {
            player.receiveCard(deck.drawCard());
            player.receiveCard(deck.drawCard());
        }
        dealer.receiveCard(deck.drawCard());
        dealer.receiveCard(deck.drawCard());
        outputView.printFirstCardsGiven(players, dealer);
        outputView.printDealerCard(dealer);
        outputView.printPlayersCard(players);
    }

    private void drawCardToPlayers(List<Player> players, Deck deck) {
        for (Player player : players) {
            drawCardToPlayer(player, deck);
        }
    }

    private void drawCardToPlayer(Player player, Deck deck) {
        DrawCardRequestDto drawCardRequest = inputView.getPlayersResponse(player);
        while (player.drawable() && drawCardRequest.isYes()) {
            player.receiveCard(deck.drawCard());

            outputView.printCards(player);
            drawCardRequest = inputView.getPlayersResponse(player);
        }
    }

    private void drawCardToDealer(Dealer dealer, Deck deck) {
        while (dealer.drawable()) {
            dealer.receiveCard(deck.drawCard());
            outputView.printDealerCardGiven();
        }
    }
}
