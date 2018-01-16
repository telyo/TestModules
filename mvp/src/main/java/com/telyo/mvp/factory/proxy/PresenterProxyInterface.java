package com.telyo.mvp.factory.proxy;

import com.telyo.mvp.base.BasePresenter;
import com.telyo.mvp.base.IBaseView;
import com.telyo.mvp.factory.PresenterMvpFactory;

/**
 * Created by Administrator on 2017/12/7.
 * @ description 代理接口
 */

public interface PresenterProxyInterface<V extends IBaseView,P extends BasePresenter<V>> {
    /**
     * 设置创建Presenter的工厂
     * @param presenterFactory PresenterFactory 类型
     */
    void setPresenterFactory(PresenterMvpFactory<V,P> presenterFactory);

    /**
     * 获取Presenter的工厂类
     * @return PresenterFactory类型
     */
    PresenterMvpFactory<V,P> getPresenterFactory();

    /**
     * 获取创建的Presenter
     * @return 指定的Presenter类型
     */
    P getMvpPresenter();
}
