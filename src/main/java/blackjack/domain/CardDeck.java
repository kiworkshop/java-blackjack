package blackjack.domain;

import blackjack.enums.Denomination;
import blackjack.enums.Suit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardDeck {
    private int index;
    private static final List<Card> CARD_DECK_CACHE = new ArrayList<>();

    static {
        for (Denomination denomination : Denomination.values()) {
            for (Suit suit : Suit.values()) {
                CARD_DECK_CACHE.add(new Card(denomination, suit));
            }
        }
    }

    public CardDeck() {
        init();
    }

    public Card getAdditionalCard() {
        if (index < 1) {
            throw new IndexOutOfBoundsException("더이상 카드가 없습니다.");
        }
        return CARD_DECK_CACHE.get(--index);
    }

    public int getIndex() {
        return index;
    }

    private void init(

    ) {
        index = CARD_DECK_CACHE.size();
        suffleCard();
    }

    private void suffleCard() {
        Collections.shuffle(CARD_DECK_CACHE);
    }
}
