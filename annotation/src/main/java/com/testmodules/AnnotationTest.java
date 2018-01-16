package com.testmodules;

import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by Administrator on 2017/12/5.
 */

public class AnnotationTest<T> {
    
    public void test(Class<T> clazz) throws ClassNotFoundException,SecurityException,NoSuchMethodException{
        boolean flag = clazz.isAnnotationPresent(TestAnnotationClass.class);
        if (flag){
            Log.e("AnnotationTest","判断类型是annotation");
            TestAnnotationClass annotationClass = clazz.getAnnotation(TestAnnotationClass.class);
            Log.e("AnnotationTest","annotationClass.value() = " + annotationClass.value());
    }

        Method[] methods = clazz.getMethods();
        for (Method method : methods){
            if (method.isAnnotationPresent(TestAnnotationMethod.class)){
                Log.e("AnnotationTest",method.getName() + "is a annotation and \n");
                TestAnnotationMethod annotationMethod = method.getAnnotation(TestAnnotationMethod.class);
                Log.e("AnnotationTest",annotationMethod.test() + annotationMethod.isAnnotation());
            }
        }
    }
}
