package com.telyo.mvp.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.telyo.mvp.factory.PresenterMvpFactory;
import com.telyo.mvp.factory.PresenterMvpFactoryImpl;
import com.telyo.mvp.factory.proxy.BaseMvpProxy;
import com.telyo.mvp.factory.proxy.PresenterProxyInterface;

/**
 * Created by Administrator on 2017/12/7.
 * @description 继承自Activity 的基类MvpActivity
 * 使用代理模式来代理Presenter的创建，销毁，绑定、解绑，以及Presenter的状态保存
 * 其实就是管理Presenter的生命周期
 */

public abstract class AbstractMvpActivity<V extends IBaseView,P extends BasePresenter<V>>
         extends Activity  implements PresenterProxyInterface<V,P>{
    private static final String PRESENTER_SAVE_KEY = "presenter_save_key";

    /**
     * 创建代理对象，传入默认的Presenter工厂
     */
    private BaseMvpProxy<V,P> mProxy = new BaseMvpProxy<>(PresenterMvpFactoryImpl.<V,P>createFactory(getClass()));

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("perfect-mvp", "V onCreate");
        Log.e("perfect-mvp", "V onCreate mProxy = " + mProxy);
        Log.e("perfect-mvp", "V onCreate this = " + this.hashCode());
        
        if (savedInstanceState != null){
            mProxy.onRestoreInstanceState(savedInstanceState.getBundle(PRESENTER_SAVE_KEY));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("perfect-mvp", "V onResume");
        mProxy.onResume((V)this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("perfect-mvp", "V onDestroy");
        mProxy.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e("perfect-mvp", "V onSaveInstanceState");
        outState.putBundle(PRESENTER_SAVE_KEY,mProxy.onSaveInstanceState());
    }

 

    @Override
    public void setPresenterFactory(PresenterMvpFactory<V, P> presenterFactory) {
        Log.e("perfect-mvp", "V setPresenterFactory");
        mProxy.setPresenterFactory( presenterFactory);
    }

    @Override
    public PresenterMvpFactory<V, P> getPresenterFactory() {
        Log.e("perfect-mvp", "V getPresenterFactory");

        return mProxy.getPresenterFactory();
    }
    
    @Override
    public P getMvpPresenter(){
        return mProxy.getMvpPresenter();
    }
}
