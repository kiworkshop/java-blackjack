package blackjack.dto;

import blackjack.utils.StringUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class DrawCardRequestDto {

    private final String response;

    public boolean isYes() {
        String trimmedResponse = response.trim();
        StringUtil.validateYesOrNo(trimmedResponse);
        return trimmedResponse.equalsIgnoreCase("y");
    }
}
