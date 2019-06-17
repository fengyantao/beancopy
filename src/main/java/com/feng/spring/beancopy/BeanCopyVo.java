package com.feng.spring.beancopy;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: fengyantao[916942072@qq.com]
 * @date: 2019/6/14 下午3:34
 * @version: V1.0
 * @review: fengyantao[916942072@qq.com]/2019/6/14 下午3:34
 */

public class BeanCopyVo {

    public static void copyProperties(Object source, Object target) throws BeansException {

        Assert.notNull(source, "Source must not be null");
        Assert.notNull(target, "Target must not be null");

        Class<?> actualEditable = target.getClass();
        PropertyDescriptor[] targetPds = BeanUtils.getPropertyDescriptors(actualEditable);

        Field[] fields = actualEditable.getDeclaredFields();

        Map<String, Field> map = new HashMap<>();
        for (Field f : fields) {
            map.put(f.getName(), f);
        }

        for (PropertyDescriptor targetPd : targetPds) {
            Method writeMethod = targetPd.getWriteMethod();

            if (null == writeMethod) {
                continue;
            }

            //获取注解
            String fieldName = targetPd.getName();
            Field field = map.get(fieldName);
            if (null == field) {
                continue;
            }

            if (!field.isAnnotationPresent(RefAnnotation.class)) {
                continue;
            }
            RefAnnotation annotation = field.getAnnotation(RefAnnotation.class);
            String[] dec = annotation.dec();

            if (writeMethod != null) {
                PropertyDescriptor sourcePd = null;
                for (String methodName : dec) {
                    sourcePd = BeanUtils.getPropertyDescriptor(source.getClass(), methodName);

                    if (null == sourcePd) {
                        continue;
                    }

                    Method readMethod = sourcePd.getReadMethod();
                    if (readMethod != null && ClassUtils
                            .isAssignable(writeMethod.getParameterTypes()[0], readMethod.getReturnType())) {
                        try {
                            if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
                                readMethod.setAccessible(true);
                            }
                            Object value = readMethod.invoke(source);
                            if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
                                writeMethod.setAccessible(true);
                            }
                            writeMethod.invoke(target, value);
                        } catch (Throwable ex) {
                            throw new FatalBeanException(
                                    "Could not copy property '" + targetPd.getName() + "' from source to target", ex);
                        }
                    }
                }

            }
        }
    }
}
