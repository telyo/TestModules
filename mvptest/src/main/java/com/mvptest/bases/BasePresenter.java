package com.mvptest.bases;

/**
 * Created by Administrator on 2017/12/11.
 */

public class BasePresenter<V extends IBaseView> {
    private V view;
    
    public void attachView(V v) {
        view = v;
    }

    public void detachView() {
        view = null;
    }

    public V getView() {
        return view;
    }
}
