package blackjack.domain.result;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

import java.util.*;

public class TotalResult {

    private final Map<Player, WinningResult> playersResult = new LinkedHashMap<>();
    private final Map<WinningResult, Integer> dealerResult = new LinkedHashMap<>();

    private TotalResult(Dealer dealer, List<Player> players) {
        // 딜러 기준 승패를 가리기
        // winningResult를 넣은 Map에다가 키 값으로 숫자(승패 숫자)
        Arrays.stream(WinningResult.values())
                .forEach(winningResult ->
                        dealerResult.put(winningResult, 0));
    }


    public static TotalResult of(Dealer dealer, List<Player> players) {
        return new TotalResult(dealer, players);
    }


}

