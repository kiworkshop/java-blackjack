package blackjack.service;

import blackjack.domain.game.Table;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.dto.DealerDto;
import blackjack.dto.FinalDealerDto;
import blackjack.dto.ParticipantDto;
import blackjack.dto.PlayerDto;

import java.util.ArrayList;
import java.util.List;

public class BlackjackService {
    private final Table table;

    public BlackjackService(List<String> playerNames) {
        this.table = new Table(playerNames);
    }

    public ParticipantDto getParticipants() {
        DealerDto dealerDto = generateDealerDto();
        List<PlayerDto> playersDto = generatePlayerDto();

        return new ParticipantDto(dealerDto, playersDto);
    }

    public ParticipantDto getFinalParticipants() {
        DealerDto finalDealerDto = generateFinalDealerDto();
        List<PlayerDto> playersDto = generatePlayerDto();

        return new ParticipantDto(finalDealerDto, playersDto);
    }

    private DealerDto generateDealerDto() {
        Dealer dealer = table.getDealer();
        return new DealerDto(dealer.getFaceUpCard());
    }

    private FinalDealerDto generateFinalDealerDto() {
        Dealer dealer = table.getDealer();
        return new FinalDealerDto(dealer);
    }

    private List<PlayerDto> generatePlayerDto() {
        List<PlayerDto> playersDto = new ArrayList<>();
        table.getPlayers().forEach(player -> playersDto.add(new PlayerDto(player)));
        return playersDto;
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
