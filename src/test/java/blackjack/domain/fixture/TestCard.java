package blackjack.domain.fixture;

import blackjack.domain.card.Card;
import blackjack.domain.card.Suit;

public class TestCard {

    public static final Card ACE_1 = Card.get(Suit.CLUB, "A");
    public static final Card ACE_2 = Card.get(Suit.DIAMOND, "A");
    public static final Card ACE_3 = Card.get(Suit.HEART, "A");
    public static final Card ACE_4 = Card.get(Suit.SPADE, "A");

    public static final Card CARD_2 = Card.get(Suit.SPADE, 2);
    public static final Card CARD_3 = Card.get(Suit.SPADE, 3);
    public static final Card CARD_4 = Card.get(Suit.SPADE, 4);
    public static final Card CARD_5 = Card.get(Suit.SPADE, 5);
    public static final Card CARD_6 = Card.get(Suit.SPADE, 6);
    public static final Card CARD_7 = Card.get(Suit.SPADE, 7);
    public static final Card CARD_8 = Card.get(Suit.SPADE, 8);
    public static final Card CARD_9 = Card.get(Suit.SPADE, 9);
    public static final Card CARD_10 = Card.get(Suit.SPADE, 10);
    public static final Card CARD_K = Card.get(Suit.SPADE, "K");
    public static final Card CARD_J = Card.get(Suit.SPADE, "J");
    public static final Card CARD_Q = Card.get(Suit.SPADE, "Q");
}
