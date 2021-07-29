package blackjack.domain.card;

import lombok.Getter;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Deck {

    private static final Random RANDOM = new Random();
    private static final String ALERT_NO_CARD_LEFT = "사용 가능한 카드를 모두 소진하였습니다.";

    @Getter
    private final List<Card> deck;

    public Deck() {
        this.deck = new LinkedList<>(DeckFactory.createDeck());
    }

    public Card drawCard() {
        return deck.remove(generateRandomNumber());
    }

    private int generateRandomNumber() {
        return RANDOM.nextInt(deck.size());
    }

}
