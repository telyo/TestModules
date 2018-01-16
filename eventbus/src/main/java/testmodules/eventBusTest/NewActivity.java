package testmodules.eventBusTest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/12/5.
 */

public class NewActivity extends AppCompatActivity {

    TestMessageEvent messageEvent;

    @Bind(R.id.tv_startMessage)
    TextView tvStartMessage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        ButterKnife.bind(this);
        messageEvent = new TestMessageEvent("粘性事件");
    }
    
    @OnClick(R.id.tv_startMessage)
    public void onViewClicked() {
        EventBus.getDefault().postSticky(messageEvent);
        finish();
    }
}
