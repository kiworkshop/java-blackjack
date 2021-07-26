package blackjack.domain.game;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.dto.DealerDto;
import blackjack.dto.ParticipantDto;
import blackjack.dto.PlayerDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Blackjack {

    private final Table table;

    public Blackjack(List<String> players) {
        this.table = new Table(players);
    }

    public ParticipantDto getParticipants() {
        Dealer dealer = table.getDealer();
        DealerDto dealerDto = new DealerDto(Collections.singletonList(dealer.getFirstHand()));

        List<Player> players = table.getPlayers();
        List<PlayerDto> playersDto = new ArrayList<>();
        players.forEach(player -> playersDto.add(new PlayerDto(player.getName(), player.getCards())));

        return new ParticipantDto(dealerDto, playersDto);
    }
}
