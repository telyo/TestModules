package com.testmodules;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Administrator on 2017/12/5.
 */

@Target(value = ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TestAnnotationClass {
    String value();
}
