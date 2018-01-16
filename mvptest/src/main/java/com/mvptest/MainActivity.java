package com.mvptest;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mvptest.bases.BaseMvpActivity;
import com.mvptest.bases.presenterfactory.CreatePresenter;
import com.mvptest.mvp.presenter.MainPresenter;
import com.mvptest.mvp.view.IMainView;

@CreatePresenter(MainPresenter.class)
public class MainActivity extends BaseMvpActivity<IMainView,MainPresenter> implements IMainView, View.OnClickListener {

    private TextView mTvContent;
    private Button mBtnGetContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mTvContent = findViewById(R.id.tv_content);
        mBtnGetContent = findViewById(R.id.btn_getContent);
        mBtnGetContent.setOnClickListener(this);
    }

    @Override
    protected void initDate() {
    }
    
    @Override
    public void onLoading() {
        mTvContent.setText("加载中...");
    }

    @Override
    public void onRequestSuccess(String s) {
        mTvContent.setText(s);
    }

    @Override
    public void onRequestFail(Throwable e) {
        toast(e.getMessage());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_getContent:
                getPresenter().doRequest();
                break;
        }
    }
}
