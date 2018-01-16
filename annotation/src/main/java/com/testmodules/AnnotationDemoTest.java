package com.testmodules;

import android.util.Log;

/**
 * Created by Administrator on 2017/12/5.
 */

@TestAnnotationClass("测试我的注解类")
public class AnnotationDemoTest implements IProxy{
    
    @TestAnnotationMethod(test = "",isAnnotation = true)
    public void testAnnotation(){
        Log.e("AnnotationDemoTest","hello annotation");
    }
}
