package com.jazz.pipeline;

/**
 * 第三个阀门，将传入的字符串中”zz”替换成”yy”
 * Created by XUJING592 on 2018/1/25.
 */
public class ThirdValve implements Valve {
    protected Valve next = null;
    public Valve getNext() {
        return next;
    }
    public void invoke(String handling) {
        handling = handling.replaceAll("zz", "yy");
        System.out.println("Third阀门处理完后：" + handling);
        getNext().invoke(handling);
    }
    public void setNext(Valve valve) {
        this.next = valve;
    }
}