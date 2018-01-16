package com.telyo.mvp.factory;

import com.telyo.mvp.base.BasePresenter;
import com.telyo.mvp.base.IBaseView;

/**
 * Created by Administrator on 2017/12/7.
 */

public class PresenterMvpFactoryImpl<V extends IBaseView,P extends BasePresenter<V>> implements PresenterMvpFactory<V,P> {
    /**
     * 需要创建的Presenter
     */
    private final Class<P> mPresenterClass;

    private PresenterMvpFactoryImpl(Class<P> mPresenterClass) {
        this.mPresenterClass = mPresenterClass;
    }

    /**
     * 根据注解创建Presenter的工厂类
     * @param viewClazz 需要创建Presenter的V层实现类
     * @param <V> 当前View实现的接口类型
     * @param <P> 当前要创建的Presenter类型
     * @return 工厂类
     */
    public static <V extends IBaseView,P extends BasePresenter<V>> PresenterMvpFactoryImpl<V,P> createFactory(Class<?> viewClazz){
        CreatePresenter annotation = viewClazz.getAnnotation(CreatePresenter.class);
        Class<P> aClass = null;
        if (annotation != null){
            aClass = (Class<P>) annotation.value();
        }
        return aClass == null ? null : new PresenterMvpFactoryImpl<V, P>(aClass);
    }

    @Override
    public P createMvpPresenter() {
        try {
            return mPresenterClass.newInstance();
        }catch (Exception e){
            throw new RuntimeException("Presenter 创建失败");
        }
    }
}
