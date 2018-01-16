package com.telyo.mvp.factory.proxy;

import android.os.Bundle;
import android.util.Log;

import com.telyo.mvp.base.BasePresenter;
import com.telyo.mvp.base.IBaseView;
import com.telyo.mvp.factory.PresenterMvpFactory;

import java.lang.reflect.Method;

/**
 * Created by Administrator on 2017/12/7.
 * 管理Presenter生命周期方法
 * 
 * 双重代理 既代理了工厂创建Presenter 又代理了创建出来的Presenter的生命周期
 */

public class BaseMvpProxy<V extends IBaseView,P extends BasePresenter<V>> implements PresenterProxyInterface<V,P>  {
    /**
     * 获取onSaveInstanceState中bundle的key
     */
    private static final String PRESENTER_KEY = "presenter_key";
    /**
     * 工厂类
     */
    private PresenterMvpFactory<V,P> mFactory; 
    private P mPresenter;
    private Bundle mBundle;
    private boolean mIsAttachView;
    
    public BaseMvpProxy(PresenterMvpFactory<V,P> presenterMvpFactory){
        this.mFactory = presenterMvpFactory;
        
    }

    /**
     * 设置Presenter的工厂类，这个方法只能在创建Presenter之前调用，也就是getMvpPresenter()之前调用，如果Presenter已创建就不能再修改
     * @param presenterFactory PresenterFactory 类型
     */
    @Override
    public void setPresenterFactory(PresenterMvpFactory<V, P> presenterFactory) {
        if (mPresenter!=null){
            throw new IllegalArgumentException("这个方法只能在getMvpPresenter()之前调用，如果Presenter已创建就不能再修改");
        }
        this.mFactory = presenterFactory;
    }

    /**
     * 获取Presenter的工厂类
     * @return PresenterMvpFactory的工厂类
     */
    @Override
    public PresenterMvpFactory<V, P> getPresenterFactory() {
        return mFactory;
    }

    /**
     * 获取创建的Presenter
     * @return 指定类型的Presenter
     * 如果之前创建过，而且是意外销毁则从Bundle中恢复
     */
    @Override
    public P getMvpPresenter() {
        Log.e("perfect-mvp","Proxy getMvpPresenter");
        if (mFactory!=null){
            if (mPresenter == null){
                mPresenter = mFactory.createMvpPresenter();
                mPresenter.onCreatePresenter(mBundle == null? null:mBundle.getBundle(PRESENTER_KEY));
            }
        }
        Log.e("perfect-mvp","Proxy getMvpPresenter =" + mPresenter);

        return mPresenter;
    }

    /**
     * 绑定Presenter和View
     * @param mvpView 对应的View
     */
    public void onResume(V mvpView){
        getMvpPresenter();
        Log.e("perfect-mvp","Proxy onResume");
        if (mPresenter != null && !mIsAttachView){
            mPresenter.attachMvpView(mvpView);
            mIsAttachView = true;
        }
    }

    /**
     * 销毁Presenter持有的View
     */
    public void onDetachMvpView(){
        if (mPresenter != null && mIsAttachView){
            mPresenter.detachMvpView();
            mIsAttachView = false;
        }
    }
    
    /**
     * 销毁Presenter
     */
    public void onDestroy(){
        Log.e("perfect-mvp","Proxy onDestroy");
        if (mPresenter != null){
            onDetachMvpView();
            mPresenter.onDestroyPresenter();
            mPresenter = null;
        }
    }

    /**
     * 意外销毁的时候调用
     * @return 存入Presenter的Bundle和当前presenter的Id
     */
    public Bundle onSaveInstanceState(){
        Log.e("perfect-mvp","Proxy onSaveInstanceState");
        Bundle bundle = new Bundle();
        getMvpPresenter();
        if (mPresenter != null){
            Bundle presenterBundle = new Bundle();
            //回调Presenter保存状态数据
            mPresenter.onSaveInstanceState(presenterBundle);
            bundle.putBundle(PRESENTER_KEY,presenterBundle);
        }
        return bundle;
    }

    /**
     * 意外关闭 恢复Presenter
     * @param savedInstanceState 意外关闭储存的Bundle
     */
    public void onRestoreInstanceState(Bundle savedInstanceState){
        Log.e("perfect-mvp","Proxy onRestoreInstanceState = ");
        Log.e("perfect-mvp","Proxy onRestoreInstanceState Presenter= ");
        mBundle = savedInstanceState;
    }
}
