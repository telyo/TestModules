package com.telyo.mvp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.telyo.mvp.base.AbstractMvpActivity;
import com.telyo.mvp.base.BaseMvpActivity;
import com.telyo.mvp.factory.CreatePresenter;
import com.telyo.mvp.mvp.presenter.MainPresenter;
import com.telyo.mvp.mvp.view.IMainView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

@CreatePresenter(MainPresenter.class)
public class MainActivity extends AbstractMvpActivity<IMainView,MainPresenter> implements IMainView {

    @Bind(R.id.tv_content)
    TextView tvContent;
    @Bind(R.id.btn_mvpClick)
    Button btnMvpClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        
        if (savedInstanceState!= null){
            Log.e("perfect-mvp","MainActivity onCreate 测试 = " + savedInstanceState.getString("test"));   
        }

    }

    private void request(){
        getMvpPresenter().clickRequest();
    }
   

    @OnClick(R.id.btn_mvpClick)
    public void onViewClicked() {
        request();
    }

    @Override
    public void onLoading() {
        tvContent.setText("加载中");
    }

    @Override
    public void onRequestSuccess(String s) {
        tvContent.setText(s);
    }

    @Override
    public void onRequestFail(Throwable e) {
        tvContent.setText(e.getMessage());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("test","test_save");
    }
}
