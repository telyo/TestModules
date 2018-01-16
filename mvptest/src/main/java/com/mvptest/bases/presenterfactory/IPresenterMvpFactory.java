package com.mvptest.bases.presenterfactory;

import android.content.Context;

import com.mvptest.bases.BasePresenter;
import com.mvptest.bases.IBaseView;

/**
 * Created by Administrator on 2017/12/11.
 */

public interface IPresenterMvpFactory<V extends IBaseView,P extends BasePresenter<V>>{
    P createPresenter(Context context);
}
