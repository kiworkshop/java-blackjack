package blackjack.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class DrawCardRequestDto {
    private static final String RESPONSE_RESTRICT_MESSAGE = "y 혹은 n 만 입력할 수 있습니다.";

    private final String response;

    public boolean isYes() {
        validateYesOrNo();
        return response.trim().equalsIgnoreCase("y");
    }

    public void validateYesOrNo() {
        String trimmedResponse = response.trim();
        if (!(trimmedResponse.equals("Y") || trimmedResponse.equals("y") || trimmedResponse.equals("N") || trimmedResponse.equals("n"))) {
            throw new IllegalArgumentException(RESPONSE_RESTRICT_MESSAGE);
        }
    }
}
