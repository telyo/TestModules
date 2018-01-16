package com.mvptest.bases.presenterfactory;

import android.content.Context;
import android.util.Log;

import com.mvptest.bases.BasePresenter;
import com.mvptest.bases.IBaseView;

import java.lang.reflect.Constructor;

/**
 * Created by Administrator on 2017/12/11.
 */

public class PresenterFactoryImpl<V extends IBaseView,P extends BasePresenter<V>> implements IPresenterMvpFactory<V,P> {
    private Class<P> mPresenterClass;
    
    private PresenterFactoryImpl(Class<P> pClass){
        mPresenterClass = pClass;
        
    }
    
    public static <V extends IBaseView,P extends BasePresenter<V>> PresenterFactoryImpl<V,P> createFactory(Class<?> viewClass){
        CreatePresenter annotation = viewClass.getAnnotation(CreatePresenter.class);
        Class<P> pClass = null;
        if (annotation != null){
            pClass = (Class<P>) annotation.value();
        }
        return pClass == null ? null : new PresenterFactoryImpl<V, P>(pClass);
    }
    
    @Override
    public P createPresenter(Context context) {
        try {
            //用反射 创建有参数的实例
            Class[] parameterTypes={Context.class};
            Constructor constructor=mPresenterClass.getConstructor(parameterTypes);
            Object[] parameters={context};
            return (P)constructor.newInstance(parameters);
            
        } catch (Exception e) {
            Log.e("Tag",e.getMessage());
            throw new RuntimeException("presenter 创建失败");
        }
    }
}
