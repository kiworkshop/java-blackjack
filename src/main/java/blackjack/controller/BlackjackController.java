package blackjack.controller;

import blackjack.service.BlackjackService;
import blackjack.domain.participant.Player;
import blackjack.dto.ParticipantDto;
import blackjack.dto.PlayerDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;

public class BlackjackController {

    // 1. Table 객체를 생성하면 participants가 세팅 된다. 패가 다 돌아감.
    // 2. 플레이어 마다 hit / stand 여부에 따라 패를 추가한다.
    // 3. 모든 플레이어가 카드를 받은 경우, 딜러의 카드를 추가 할지 판단한다.
    // 4. 최종 결과를 표출한다.
    public void run() {
        List<String> players = InputView.getPlayers();
        BlackjackService blackjackService = new BlackjackService(players);
        OutputView.printInitialDeal(blackjackService.getParticipants());

        ParticipantDto participants = deal(blackjackService);
        OutputView.printFinalHands(participants);
    }

    private ParticipantDto deal(BlackjackService blackjackService) {
        dealEachPlayer(blackjackService);
        blackjackService.dealDealer();
        return blackjackService.getParticipants();
    }

    private void dealEachPlayer(BlackjackService blackjackService) {
        for (Player player : blackjackService.getPlayers()) {
            hitOrStand(blackjackService, player);
        }
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
