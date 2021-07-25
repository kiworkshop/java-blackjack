package blackjack.domain;

import blackjack.constant.Denomination;
import blackjack.constant.Type;
import lombok.Getter;

import java.util.LinkedList;
import java.util.List;

public class Deck {

    @Getter
    private final List<Card> deck = new LinkedList<>();

    public Deck() {
        for (Denomination denomination : Denomination.values()) {
            for (Type type : Type.values()) {
                deck.add(new Card(denomination, type));
            }
        }
    }

}
