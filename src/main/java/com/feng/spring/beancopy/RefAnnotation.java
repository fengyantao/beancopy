
package com.feng.spring.beancopy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author: fengyantao[916942072@qq.com]
 * @date: 2019/6/14 下午2:39
 * @version: V1.0
 * @review: fengyantao[916942072@qq.com]/2019/6/14 下午2:39
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RefAnnotation {

    String[] dec() default {};
}
