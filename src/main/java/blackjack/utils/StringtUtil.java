package blackjack.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringtUtil {
    private static final String COMMA = ",";

    public static List<String> splitByComma(String input) {
        List<String> names = Arrays.asList(input.split(COMMA));
        return names.stream()
                .map(String::trim)
                .collect(Collectors.toList());
    }
}
