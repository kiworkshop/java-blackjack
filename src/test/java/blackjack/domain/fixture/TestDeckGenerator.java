package blackjack.domain.fixture;

import blackjack.domain.card.Card;
import blackjack.domain.game.DeckGenerator;

import java.util.ArrayList;
import java.util.List;

import static blackjack.domain.fixture.TestCard.*;

public class TestDeckGenerator implements DeckGenerator {

    public static final int TEST_DECK_INITIAL_SIZE = 10;

    @Override
    public List<Card> generateCards() {
        List<Card> cards = new ArrayList<>();

        cards.add(ACE_1);
        cards.add(CARD_2);
        cards.add(CARD_K);
        cards.add(CARD_3);
        cards.add(CARD_Q);
        cards.add(CARD_4);
        cards.add(CARD_J);
        cards.add(CARD_5);
        cards.add(CARD_6);
        cards.add(CARD_7);

        return cards;
    }
}
