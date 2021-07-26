package blackjack.domain;

import blackjack.enums.Denomination;
import blackjack.enums.Type;
import lombok.Getter;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Deck {

    private static final Random RANDOM = new Random();
    private static final String ALERT_NO_CARD_LEFT = "사용 가능한 카드를 모두 소진하였습니다.";

    @Getter
    private final List<Card> deck = new LinkedList<>();

    public Deck() {
        for (Denomination denomination : Denomination.values()) {
            for (Type type : Type.values()) {
                deck.add(new Card(denomination, type));
            }
        }
    }

    public Card drawCard(int index) {
        return deck.remove(index);
    }

}
