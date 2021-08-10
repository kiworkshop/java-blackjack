package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeckFactory {

    private static List<Card> cardsDeck = createCardsDeck();

    private static List<Card> createCardsDeck() {
        List<Card> cards = new ArrayList<>();

        for (Denomination denomination : Denomination.values()) {
            cards.addAll(createCards(denomination));
        }
        return cards;
    }

    private static List<Card> createCards(Denomination denomination) {
        List<Card> cards = new ArrayList<>();

        for (Type type : Type.values()) {
            cards.add(new Card(denomination, type));
        }

        return cards;
    }

    public static List<Card> createDeck() {
        return Collections.unmodifiableList(cardsDeck);
    }
}


