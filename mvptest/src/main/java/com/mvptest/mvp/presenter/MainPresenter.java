package com.mvptest.mvp.presenter;

import android.content.Context;

import com.mvptest.bases.BasePresenter;
import com.mvptest.mvp.mode.MainMode;
import com.mvptest.mvp.view.IMainView;

/**
 * Created by Administrator on 2017/12/11.
 */

public class MainPresenter extends BasePresenter<IMainView>{
    private MainMode mMainMode;
    private Context mContext;
    public MainPresenter(Context context){
        mMainMode = new MainMode();
        mContext = context;
    }
    public void doRequest(){
        mMainMode.requestSomething(mContext, new MainMode.Listener() {
            @Override
            public void onLoading() {
                getView().onLoading();
            }

            @Override
            public void onSuccess(String s) {
                getView().onRequestSuccess(s);
            }

            @Override
            public void onError(Exception e) {
                getView().onRequestFail(e);
            }
        });
    }
}
