package com.telyo.mvp.mvp.presenter;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.telyo.mvp.base.BasePresenter;
import com.telyo.mvp.mvp.mode.MainMode;
import com.telyo.mvp.mvp.view.IMainView;

/**
 * Created by Administrator on 2017/12/7.
 */

public class MainPresenter extends BasePresenter<IMainView> {
    private MainMode mMainMode;
    public MainPresenter(){
        mMainMode = new MainMode();
    }
    public void clickRequest(){
        //获取View
        if (getMvpView()!=null){
            getMvpView().onLoading();
        }
        mMainMode.getDate(new MainMode.CallBack() {
            @Override
            public void onError(Throwable e) {
                if (getMvpView()!=null) {
                    getMvpView().onRequestFail(e);
                }
            }

            @Override
            public void onSuccess(String s) {
                if (getMvpView()!=null) {
                    getMvpView().onRequestSuccess(s);
                }
            }
        });
    }
    
    public void interruptHttp(){
        mMainMode.interruptHttp();
    }
    
}
