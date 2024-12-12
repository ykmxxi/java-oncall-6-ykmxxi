package oncall.presentation.view;

import java.util.Objects;
import java.util.regex.Pattern;

public final class InputValidator {

    private static final Pattern COMMA_INPUT_PATTERN =
            Pattern.compile("^[\\wㄱ-ㅎ가-힣]+(,\\s?[\\wㄱ-ㅎ가-힣]+)+$");

    public static void validate(final String input) {
        validateBlank(input);
        validatePattern(input);
    }

    private static void validateBlank(final String input) {
        if (Objects.isNull(input) || input.isBlank()) {
            throw new IllegalArgumentException("값을 입력해 주세요.");
        }
    }

    private static void validatePattern(final String input) {
        if (!COMMA_INPUT_PATTERN.matcher(input)
                .matches()
        ) {
            throw new IllegalArgumentException("쉼표로 구분해 입력해 주세요.");
        }
    }

}
