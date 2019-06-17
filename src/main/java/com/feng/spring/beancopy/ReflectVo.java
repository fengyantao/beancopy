package com.feng.spring.beancopy;

import java.io.Serializable;

/**
 * @author: fengyantao[916942072@qq.com]
 * @date: 2019/6/14 下午2:38
 * @version: V1.0
 * @review: fengyantao[916942072@qq.com]/2019/6/14 下午2:38
 */
public class ReflectVo implements Serializable {
    private static final long serialVersionUID = 8827950221777954148L;

    @RefAnnotation(dec = {"aaa", "bbb", "ccc"})
    private String name;

    @RefAnnotation(dec = {"ddd", "eee"})
    private String scott;

    public ReflectVo() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScott() {
        return scott;
    }

    public void setScott(String scott) {
        this.scott = scott;
    }
}
