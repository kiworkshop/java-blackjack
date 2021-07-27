package blackjack.domain;

import blackjack.enums.Denomination;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Player {

    private final String name;
    private final List<Card> cards;

    public Player(String name) {
        this.name = name;
        this.cards = new ArrayList<>();
    }

    public void receiveCard(Card card) {
        cards.add(card);
    }

//    public void validateName(String name){
//        if (StringUtils.isBlank(name)) {
//            throw new IllegalArgumentException("이름이 빈 칸 혹은 null 값이 아닌지 확인해주세요.");
//        }
//    }
}
