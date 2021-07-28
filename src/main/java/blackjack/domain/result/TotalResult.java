package blackjack.domain.result;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

import java.util.*;

public class TotalResult {

    private final Map<Player, WinningResult> playersResult = new LinkedHashMap<>();
    private final Map<WinningResult, Integer> dealerResult = new LinkedHashMap<>();

    private TotalResult(Dealer dealer, List<Player> players) {
        // 플레이어 기준 승패를 가리기
        // winningResult를 넣은 Map에다가 키 값으로 숫자(승패 숫자)
        Arrays.stream(WinningResult.values())
                .forEach(winningResult ->
                        dealerResult.put(winningResult, 0));

        // 플레이어 점수랑 딜러 점수 비교하는 로직
        // 플레이어 기준으로 승패가 Map에 입력이 된 상태
        players.forEach(player -> playersResult.put(player, player.win(dealer)));

        // 넣어둔 value가 있으면 apply = computeIfPresent
        // 딜러기준으로 승패를 바꿔주고, 승패 숫자를 하나씩 올려줌
        playersResult.values()
                .forEach(winningResult -> dealerResult.computeIfPresent(winningResult.reverse(), (key, value) -> value + 1));
    }

    public static TotalResult of(Dealer dealer, List<Player> players) {
        return new TotalResult(dealer, players);
    }

    public Map<Player, WinningResult> getPlayersResult() {
        return Collections.unmodifiableMap(playersResult);
    }

    public Map<WinningResult, Integer> getDealerResult() {
        return Collections.unmodifiableMap(dealerResult);
    }
}

