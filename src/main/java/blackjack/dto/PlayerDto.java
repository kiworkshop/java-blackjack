package blackjack.dto;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Player;

import java.util.List;

public class PlayerDto {
    private final String name;
    private final List<Card> cards;
    private int rankSum;

    public PlayerDto(Player player) {
        this.name = player.getName();
        this.cards = player.getCards();
        this.rankSum = cards.stream().mapToInt(Card::getRank).sum();
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards;
    }

    public int getRankSum() {
        return rankSum;
    }
}
