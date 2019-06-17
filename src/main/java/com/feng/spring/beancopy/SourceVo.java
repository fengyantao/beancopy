package com.feng.spring.beancopy;

import java.io.Serializable;

/**
 *
 *
 * @author: fengyantao[916942072@qq.com]
 * @date: 2019/6/14 下午4:54
 * @version: V1.0
 * @review: fengyantao[916942072@qq.com]/2019/6/14 下午4:54
 */
public class SourceVo implements Serializable {
    private static final long serialVersionUID = -6564361729001137186L;

    private String aaa;
    private String ddd;

    public SourceVo() {
    }

    public String getAaa() {
        return aaa;
    }

    public void setAaa(String aaa) {
        this.aaa = aaa;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }
}
