package com.telyo.mvp.factory;

import com.telyo.mvp.base.BasePresenter;
import com.telyo.mvp.base.IBaseView;

/**
 * Created by Administrator on 2017/12/7.
 */

public interface PresenterMvpFactory<V extends IBaseView,P extends BasePresenter<V>> {
    /**
     * 创建Presenter的方法接口
     * @return
     */
    P createMvpPresenter();
}
