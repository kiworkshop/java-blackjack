package blackjack.state;

import blackjack.domain.Cards;

public class Hit implements State{
    @Override
    public State hit(Cards cards) {
        if(cards.isBust()){
            return new Bust();
        }
        if(cards.isBlackJack()){
            return new BlackJack();
        }
        if(cards.isStay()) {
            return new Stay();
        }
        return new Hit();
    }

    @Override
    public State stay() {
        return new Stay();
    }

    @Override
    public int getScore(Cards cards) {
        throw new RuntimeException("게임중에는 점수를 반환할 수 없습니다.");
    }
}
