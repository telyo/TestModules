package com.telyo.mvp.mvp.view;

import com.telyo.mvp.base.IBaseView;

/**
 * Created by Administrator on 2017/12/7.
 */

public interface IMainView extends IBaseView{
    void onLoading();
    void onRequestSuccess(String s);
    void onRequestFail(Throwable e);
}
