package blackjack.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DrawCardRequestDtoTest {
    @Test
    @DisplayName("카드를 더 받을지에 대한 응답으로 Y를 입력받으면 정상 처리된다.")
    void receiveCardRequestValidTest1() {
        //given
        DrawCardRequestDto drawCardRequestDto = new DrawCardRequestDto("Y");

        //when, then
        //예외가 터지지 않으면 통과하는 테스트
        drawCardRequestDto.validateYesOrNo();
    }

    @Test
    @DisplayName("카드를 더 받을지에 대한 응답으로 y를 입력받으면 정상 처리된다.")
    void receiveCardRequestValidTest2() {
        //given
        DrawCardRequestDto drawCardRequestDto = new DrawCardRequestDto("y");

        //when, then
        //예외가 터지지 않으면 통과하는 테스트
        drawCardRequestDto.validateYesOrNo();
    }

    @Test
    @DisplayName("카드를 더 받을지에 대한 응답으로 N을 입력받으면 정상 처리된다.")
    void receiveCardRequestValidTest3() {
        //given
        DrawCardRequestDto drawCardRequestDto = new DrawCardRequestDto("N");

        //when, then
        //예외가 터지지 않으면 통과하는 테스트
        drawCardRequestDto.validateYesOrNo();
    }

    @Test
    @DisplayName("카드를 더 받을지에 대한 응답으로 n을 입력받으면 정상 처리된다.")
    void receiveCardRequestValidTest4() {
        //given
        DrawCardRequestDto drawCardRequestDto = new DrawCardRequestDto("n");

        //when, then
        //예외가 터지지 않으면 통과하는 테스트
        drawCardRequestDto.validateYesOrNo();
    }

    @Test
    @DisplayName("카드를 더 받을지에 대한 응답으로 Y y N n 이 아닌 문자열을 입력 시 예외를 던진다.")
    void receiveCardRequestInvalidTest() {
        //given
        DrawCardRequestDto drawCardRequestDto = new DrawCardRequestDto("U");

        //when, then
        assertThatThrownBy(() -> drawCardRequestDto.validateYesOrNo())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("y 혹은 n 만 입력할 수 있습니다.");
    }
}
