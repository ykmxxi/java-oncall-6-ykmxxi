package oncall;

import oncall.presentation.OnCallClient;
import oncall.presentation.view.InputView;
import oncall.presentation.view.OutputView;
import oncall.service.OnCallService;

public class Application {

    public static void main(String[] args) {
        OnCallClient onCallClient = new OnCallClient(
                new InputView(), new OutputView(), new OnCallService()
        );
        onCallClient.run();
    }

}
