package blackjack.domain;

import blackjack.state.BlackJack;
import blackjack.state.Bust;
import blackjack.state.Hit;
import blackjack.state.State;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Participant {

    private final List<Card> cards = new ArrayList<>();
    private final String name;
    private State state;

    public Participant(String name) {
        this.name = name;
        state = new Hit();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public String getName() {
        return name;
    }

    public void addFirstTwoCards(Cards cards){
        this.cards.addAll(cards.getCards());
        this.state = state.hit(cards);
    }
    public void hit(Card card){
        cards.add(card);
        this.state = state.hit(new Cards(cards));
    }
    public boolean isBlackjack(){
        return this.state instanceof BlackJack;
    }
    public boolean isBust(){
        return this.state instanceof Bust;
    }
    public void stay(){
        this.state = state.stay();
    }

    public int getScore(){
        return state.getScore(new Cards(cards));
    }
    public int getSum(){
        Cards cards = new Cards(this.cards);
        return cards.getCardScore();
    }
}
