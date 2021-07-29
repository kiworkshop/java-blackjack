package blackjack.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StringUtilTest {

    @Test
    @DisplayName("문자열을 , 구분자를 기준으로 분리한다.")
    void splitByCommaTest() {
        //given
        String input = "Jane Doe,  John Doe, Jack Smith ";
        List<String> expectedResult = Arrays.asList("Jane Doe", "John Doe", "Jack Smith");

        //when
        List<String> result = StringUtil.splitByComma(input);

        //then
        for (int i = 0; i < result.size(); i++) {
            assertThat(result.get(i)).isEqualTo(expectedResult.get(i));
        }
    }

    @Test
    @DisplayName("카드를 더 받을지에 대한 응답으로 Y를 입력받으면 정상 처리된다.")
    void receiveCardResponseCorrectTest1() {
        //given
        String response = "Y";

        //when, then
        assertThat(StringUtil.validateYesOrNo(response)).isEqualTo("Y");
    }

    @Test
    @DisplayName("카드를 더 받을지에 대한 응답으로 y를 입력받으면 정상 처리된다.")
    void receiveCardResponseCorrectTest2() {
        //given
        String response = "y";

        //when, then
        assertThat(StringUtil.validateYesOrNo(response)).isEqualTo("y");
    }

    @Test
    @DisplayName("카드를 더 받을지에 대한 응답으로 N을 입력받으면 정상 처리된다.")
    void receiveCardResponseCorrectTest3() {
        //given
        String response = "N";

        //when, then
        assertThat(StringUtil.validateYesOrNo(response)).isEqualTo("N");
    }

    @Test
    @DisplayName("카드를 더 받을지에 대한 응답으로 n을 입력받으면 정상 처리된다.")
    void receiveCardResponseCorrectTest4() {
        //given
        String response = "n";

        //when, then
        assertThat(StringUtil.validateYesOrNo(response)).isEqualTo("n");
    }

    @Test
    @DisplayName("카드를 더 받을지에 대한 응답으로 Y y N n 이 아닌 문자열을 입력 시 예외를 던진다.")
    void receiveCardResponseErrorTest() {
        //given
        String response = "U";

        //when, then
        assertThatThrownBy(() -> StringUtil.validateYesOrNo(response))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("y 혹은 n 만 입력할 수 있습니다.");
    }


}