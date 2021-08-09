package blackjack.controller;

import blackjack.domain.card.Deck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.PlayersFactory;
import blackjack.domain.result.GameResult;
import blackjack.dto.DrawCardRequestDto;
import blackjack.dto.PlayersNameInputDto;
import blackjack.utils.StringUtil;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;

public class BlackJackController {
    private final List<Player> players;
    private final Dealer dealer;
    private final Deck deck;
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public BlackJackController() {
        PlayersNameInputDto namesInput = inputView.getPlayersName();
        this.players = PlayersFactory.createPlayers(namesInput.getPlayersName());
        this.dealer = new Dealer();
        this.deck = new Deck();
    }

    public void run() {
        drawTowCards();
        drawCardToPlayers();
        drawCardToDealer();
        outputView.printCardsResult(dealer, players);
        outputView.printGameResult(GameResult.of(dealer, players));
    }

    private void drawTowCards() {
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

    private void drawCardToPlayers() {
        for (Player player : players) {
            drawCardToPlayer(player);
        }
    }

    private void drawCardToPlayer(Player player) {
        DrawCardRequestDto drawCardRequest = inputView.getPlayersResponse(player);
        while (player.drawable() && drawCardRequest.isYes()) {
            player.receiveCard(deck.drawCard());

            outputView.printCards(player);
            drawCardRequest = inputView.getPlayersResponse(player);
        }
    }

    private void drawCardToDealer() {
        while (dealer.drawable()) {
            dealer.receiveCard(deck.drawCard());
            outputView.printDealerCardGiven();
        }
    }
}
