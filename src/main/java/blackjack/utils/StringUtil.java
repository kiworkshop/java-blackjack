package blackjack.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringUtil {
    private static final String COMMA = ",";
    private static final String CHECK_ANSWER_Y_OR_N = "y 혹은 n 만 입력할 수 있습니다.";

    public static List<String> splitByComma(String input) {
        List<String> names = Arrays.asList(input.split(COMMA));
        return names.stream()
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public static String validateYesOrNo(String response) {
        if (StringUtils.isBlank(response)) {
            throw new IllegalArgumentException(CHECK_ANSWER_Y_OR_N);
        }
        if (!(response.equals("Y") || response.equals("y") || response.equals("N") || response.equals("n"))) {
            throw new IllegalArgumentException(CHECK_ANSWER_Y_OR_N);
        }

        return response;
    }
}
