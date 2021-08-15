package blackjack.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringUtil {
    private static final String COMMA = ",";
    private static final String RESPONSE_RESTRICT_MESSAGE = "y 혹은 n 만 입력할 수 있습니다.";

    public static List<String> splitByComma(String input) {
        List<String> names = Arrays.asList(input.split(COMMA));
        return names.stream()
                .map(String::trim)
                .collect(Collectors.toList());
    }
}
