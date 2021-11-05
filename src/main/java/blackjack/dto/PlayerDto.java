package blackjack.dto;

import blackjack.domain.card.Card;
import blackjack.domain.game.Hands;
import blackjack.domain.gamer.Player;

import java.util.List;

public class PlayerDto {
    private final String name;
    private final Hands hands;

    public PlayerDto(Player player) {
        this.name = player.name();
        this.hands = player.hands();
    }

    public int rankSum() {
        return hands.sumRank();
    }

    public String name() {
        return name;
    }

    public List<Card> cards() {
        return hands.cards();
    }
}
