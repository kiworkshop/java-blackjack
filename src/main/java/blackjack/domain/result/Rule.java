package blackjack.domain.result;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import lombok.Getter;

import java.util.Arrays;
import java.util.function.BiFunction;

public enum Rule{
    // 카드 값의 합이 21을 넘으면 승패 결정 (플레이어 기준으로 선언)
    PLAYER_BUST(((player, dealer) -> player.isBust()), WinningResult.LOSE, 1),
    DEALER_BUST(((player, dealer) -> dealer.isBust()), WinningResult.WIN, 2),

    // 카드 값의 합이 21을 넘지 않으면서, 값이 더 큰쪽이 승패결정(이미 여기서 결정, BlackJack 체크 필요없음)
    PLAYER_HIGHER(((player, dealer) -> player.getCardsSum() > dealer.getCardsSum()), WinningResult.WIN, 3),
    DEALER_HIGHER(((player, dealer) -> player.getCardsSum() < dealer.getCardsSum()), WinningResult.LOSE, 4),

    // 무승부 결정
    TIES(((player, dealer) -> player.getCardsSum() == dealer.getCardsSum()), WinningResult.TIE, 5);

    private BiFunction<Player, Dealer, Boolean> compare;
    private WinningResult winningResult;
    @Getter
    private int verificationOrder;

    Rule(BiFunction<Player, Dealer, Boolean> compare, WinningResult winningResult, int verificationOrder) {
        this.compare = compare;
        this.winningResult = winningResult;
        this.verificationOrder = verificationOrder;
    }

    public Boolean findMatchingRule(Player player, Dealer dealer) {
        return compare.apply(player, dealer);
    }

    public WinningResult getWinningResult() {
        return winningResult;
    }

    public static WinningResult resultPlayerVersusDealer(Player player, Dealer dealer) {

        return Arrays.stream(Rule.values())
                .sorted(new RuleComparator())
                .filter(rule -> rule.findMatchingRule(player, dealer))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("일치하는 결과를 찾을 수 없습니다."))
                .getWinningResult();
    }
}
