package blackjack.domain;

import blackjack.enums.Denomination;
import blackjack.enums.Suit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CardDeck {
    private final List<Card> cards = new ArrayList<>();
    private int size;

    public CardDeck() {
        init();
    }

    private void init() {
        Arrays.stream(Denomination.values())
                .forEach(cardScore -> {
                    Arrays.stream(Suit.values()).forEach(cardType -> cards.add(new Card(cardScore, cardType)));
                });
        size = cards.size();
        suffleCard();
    }

    public int getSize() {
        return size;
    }

    private void suffleCard() {
        Collections.shuffle(cards);
    }

    public Card getAdditionalCard() {
        if (size < 1) {
            throw new IndexOutOfBoundsException("더이상 카드가 없습니다.");
        }

        return cards.get(--size);
    }
}
