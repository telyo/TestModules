package testmodules.eventBusTest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @Bind(R.id.tv_click)
    TextView tvClick;
    @Bind(R.id.tv_showUpdate)
    TextView tvShowUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Log.e("isnull", tvClick == null ? "true" : "false");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateByMessage(TestMessageEvent messageEvent) {
        Toast.makeText(this, messageEvent.getMessage(), Toast.LENGTH_SHORT).show();
        tvShowUpdate.setText(messageEvent.getMessage());
    }

    @Subscribe(threadMode = ThreadMode.POSTING,sticky = true)
    public void onMoonStickyEvent(TestMessageEvent messageEvent){
        Toast.makeText(this, messageEvent.getMessage(), Toast.LENGTH_SHORT).show();
        tvShowUpdate.setText(messageEvent.getMessage());
    }
    @OnClick({R.id.tv_click, R.id.tv_showUpdate})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_click:
                Intent intent = new Intent(this, NewActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_showUpdate:
                if (!EventBus.getDefault().isRegistered(this)) {
                    EventBus.getDefault().register(this);
                }
                break;
        }
    }
}
