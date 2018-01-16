package testmodules.eventBusTest;

import android.widget.Toast;

/**
 * Created by Administrator on 2017/12/5.
 */

public class TestMessageEvent {
    public final String message;

    public TestMessageEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
