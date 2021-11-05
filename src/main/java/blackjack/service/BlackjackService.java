package blackjack.service;

import blackjack.domain.game.Table;
import blackjack.domain.gamer.Player;
import blackjack.domain.prize.PrizeResults;
import blackjack.dto.DealerDto;
import blackjack.dto.GamerDto;
import blackjack.dto.PlayerDto;

import java.util.List;
import java.util.stream.Collectors;

public class BlackjackService {
    private final Table table;

    public BlackjackService(List<String> playerNames) {
        this.table = new Table(playerNames);
    }

    public GamerDto generateGamer() {
        DealerDto dealerDto = new DealerDto(table.dealer());
        List<PlayerDto> playersDto = table.players().stream()
                .map(PlayerDto::new)
                .collect(Collectors.toList());
        return new GamerDto(dealerDto, playersDto);
    }

    public PlayerDto hit(Player player) {
        return new PlayerDto(table.hit(player));
    }

    public void dealDealer() {
        table.finalDeal();
    }

    public PrizeResults calculatePrizeResults() {
        return new PrizeResults(table);
    }

    public List<Player> players() {
        return table.players();
    }
}
