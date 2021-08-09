package blackjack.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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
}