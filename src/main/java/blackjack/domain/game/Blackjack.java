package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.dto.DealerDto;
import blackjack.dto.ParticipantDto;
import blackjack.dto.PlayerDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Blackjack {
    private final Table table;
    private final Players players;

    public Blackjack(List<String> playerNames) {
        this.table = new Table();
        this.players = new Players(generatePlayers(playerNames));
    }

    private List<Player> generatePlayers(List<String> playerNames) {
        return playerNames.stream()
                .map(playerName -> new Player(playerName, table.drawInitialHands()))
                .collect(Collectors.toList());
    }

    public ParticipantDto getParticipants() {
        Dealer dealer = table.getDealer();
        DealerDto dealerDto = new DealerDto(Collections.singletonList(dealer.getFaceUpCard()));

        List<PlayerDto> playersDto = new ArrayList<>();
        players.getPlayers().forEach(player -> playersDto.add(new PlayerDto(player)));

        return new ParticipantDto(dealerDto, playersDto);
    }

    public PlayerDto hit(Player player) {
        Card card = table.drawCard();
        player.take(card);
        return new PlayerDto(player);
    }

    public void dealDealer() {
        table.finalDeal();
    }

    public List<Player> getPlayers() {
        return players.getPlayers();
    }
}
