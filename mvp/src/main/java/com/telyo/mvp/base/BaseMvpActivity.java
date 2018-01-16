package com.telyo.mvp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Administrator on 2017/12/7.
 */

public abstract class BaseMvpActivity<V extends IBaseView,P extends BasePresenter<V>> extends AppCompatActivity implements IBaseView{
    private P presenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //创建presenter
        if (presenter == null){
            presenter = createPresenter();
        }
        
        if (presenter == null){
            throw new NullPointerException("presenter 不能为空！");
        }
        //绑定V层
        presenter.attachMvpView((V)this);
    }
    //让子类实现Presenter的创建
    protected abstract P createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter!=null) {
            presenter.detachMvpView();
        }
    }
    
    public P getPresenter(){
        return presenter;
    }
}
