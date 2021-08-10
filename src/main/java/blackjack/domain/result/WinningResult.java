package blackjack.domain.result;

import lombok.Getter;

@Getter
public enum WinningResult {

    WIN("승"),
    LOSE("패"),
    TIE("무승부");

    private final String result;

    WinningResult(String result) {
        this.result = result;
    }

    public WinningResult reverse() {
        if (this == WIN) {
            return LOSE;
        }
        if (this == LOSE) {
            return WIN;
        }
        return TIE;
    }
}
