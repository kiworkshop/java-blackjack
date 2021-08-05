package blackjack.service;

import blackjack.domain.game.DeckGenerator;
import blackjack.domain.game.Table;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.prize.ParticipantsPrize;
import blackjack.dto.*;

import java.util.ArrayList;
import java.util.List;

public class BlackjackService {
    private final Table table;

    public BlackjackService(List<PlayerInput> playerInputs, DeckGenerator deckGenerator) {
        this.table = new Table(playerInputs, deckGenerator);
    }

    public ParticipantsDto getParticipants() {
        DealerDto dealerDto = generateDealerDto();
        List<PlayerDto> playersDto = generatePlayerDto();
        return new ParticipantsDto(dealerDto, playersDto);
    }

    private DealerDto generateDealerDto() {
        Dealer dealer = table.getDealer();
        return new DealerDto(dealer.getFaceUpCard());
    }

    private List<PlayerDto> generatePlayerDto() {
        List<PlayerDto> playersDto = new ArrayList<>();
        table.getPlayers().forEach(player -> playersDto.add(new PlayerDto(player)));
        return playersDto;
    }

    public boolean isDealerBlackjack() {
        return table.isDealerBlackjack();
    }

    public PlayerDto hit(Player player) {
        table.hit(player);
        return new PlayerDto(player);
    }

    public void dealDealer() {
        table.finalDeal();
    }

    public ParticipantsDto getFinalParticipants() {
        DealerDto finalDealerDto = generateFinalDealerDto();
        List<PlayerDto> playersDto = generatePlayerDto();
        return new ParticipantsDto(finalDealerDto, playersDto);
    }

    private FinalDealerDto generateFinalDealerDto() {
        Dealer dealer = table.getDealer();
        return new FinalDealerDto(dealer);
    }

    public ParticipantsPrize getPrizeResults() {
        return new ParticipantsPrize(table);
    }

    public List<Player> getPlayers() {
        return table.getPlayers();
    }
}
