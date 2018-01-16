package com.telyo.mvp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Administrator on 2017/12/7.
 */

public class BasePresenter<V extends IBaseView> {
    /**
     * V层view
     */
    private V mMvpView;

    /**
     * Presenter 被创建后调用
     * @param savedState 被意外销毁后重建后的Bundle
     */
    public void onCreatePresenter(@Nullable Bundle savedState){
        Log.e("perfect-mvp","P onCreatePresenter = ");
    }
    /**
     * 绑定V层
     * @param view
     */
    public void attachMvpView(V view){
        this.mMvpView = view;
    }

    /**
     * 解除绑定V层
     */
    public void detachMvpView(){
        mMvpView = null;
    }

    /**
     * Presenter 被销毁时调用
     */
    public void onDestroyPresenter(){
        Log.e("perfect-mvp","P onDestroyPresenter = ");
    }

    /**
     * 在Presenter意外销毁时调用，它的调用时机和Activity、fragment、View中的onSaveInstanceState时机相同
     * @param outState 意外销毁后保存的Bundle
     */
    public void onSaveInstanceState(Bundle outState){
        Log.e("perfect-mvp","P onSaveInstancePresenter = ");

    }
    /**
     * 获取V层
     * @return
     */
    public V getMvpView(){
        return mMvpView;
    }
}
