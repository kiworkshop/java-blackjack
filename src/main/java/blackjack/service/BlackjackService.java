package blackjack.service;

import blackjack.domain.game.Table;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.dto.DealerDto;
import blackjack.dto.ParticipantDto;
import blackjack.dto.PlayerDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BlackjackService {
    private final Table table;

    public BlackjackService(List<String> playerNames) {
        this.table = new Table(playerNames);
    }

    public ParticipantDto getParticipants() {
        Dealer dealer = table.getDealer();
        DealerDto dealerDto = new DealerDto(Collections.singletonList(dealer.getFaceUpCard()));

        List<PlayerDto> playersDto = new ArrayList<>();
        table.getPlayers().forEach(player -> playersDto.add(new PlayerDto(player)));

        return new ParticipantDto(dealerDto, playersDto);
    }

    public void dealDealer() {
        table.finalDeal();
    }

    public PlayerDto hit(Player player) {
        return new PlayerDto(table.hit(player));
    }

    public List<Player> getPlayers() {
        return table.getPlayers();
    }
}
