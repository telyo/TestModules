package com.mvptest.bases;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.mvptest.bases.presenterfactory.PresenterFactoryImpl;


/**
 * Created by Administrator on 2017/12/11.
 */
public abstract class BaseMvpActivity<V extends IBaseView,P extends BasePresenter<V>> extends AppCompatActivity implements IBaseView{
    private P mPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mPresenter == null) {
            mPresenter = createPresenter();
        }

        if (mPresenter == null){
            throw new NullPointerException("mPresenter 不能为空！");
        }
        mPresenter.attachView((V)this);
        setContentView(getContentView());
        initView();
        initDate();
    }

    protected abstract int getContentView();

    protected abstract void initView();

    protected abstract void initDate();

    public P createPresenter(){
        return PresenterFactoryImpl.<V,P>createFactory(getClass()).createPresenter(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter!=null) {
            mPresenter.detachView();
        }
    }
    
    public P getPresenter(){
        return mPresenter;
    }
    
    protected void toast(String s){
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();   
    }
}
