package blackjack.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringUtil {
    private static final String COMMA = ",";

    public static List<String> splitByComma(String input) {
        List<String> names = Arrays.asList(input.split(COMMA));
        return names.stream()
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public static String validateYesOrNo(String response) {
        if (!(response.equals("Y") || response.equals("y") || response.equals("N") || response.equals("n"))) {
            throw new IllegalArgumentException("y 혹은 n 만 입력할 수 있습니다.");
        }

        return response;
    }
}
