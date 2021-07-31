package blackjack.domain.state;

public abstract class Running implements State {

    @Override
    public int sum() {
        throw new IllegalStateException("게임 진행 중에는 카드의 합을 반환할 수 없습니다.");
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
