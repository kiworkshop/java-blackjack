package blackjack.domain.card;

import blackjack.domain.card.strategy.DrawStrategy;
import blackjack.domain.card.strategy.RandomDrawStrategy;
import lombok.Getter;

import java.util.LinkedList;
import java.util.List;

public class Deck {

    private static final String ALERT_NO_CARD_LEFT = "사용 가능한 카드를 모두 소진하였습니다.";

    @Getter
    private final List<Card> deck;

    public Deck() {
        this.deck = new LinkedList<>(DeckFactory.createDeck());
    }

    public Card drawCard() {
        if (deck.isEmpty()) {
            throw new RuntimeException(ALERT_NO_CARD_LEFT);
        }
        return deck.remove(generateNextDrawCardIndex(new RandomDrawStrategy()));
    }

    private int generateNextDrawCardIndex(DrawStrategy drawStrategy) {
        return drawStrategy.getNextIndex(deck);
    }

}
