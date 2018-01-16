package com.mvptest.mvp.view;

import com.mvptest.bases.IBaseView;

/**
 * Created by Administrator on 2017/12/11.
 */

public interface IMainView extends IBaseView{
    void onLoading();
    void onRequestSuccess(String s);
    void onRequestFail(Throwable e);
}
