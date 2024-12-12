package oncall.presentation;

import java.util.function.Supplier;

import oncall.presentation.view.OutputView;

public final class RetryHandler {

    private RetryHandler() {}

    public static <T> T retry(final Supplier<T> supplier, final OutputView outputView) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

}
