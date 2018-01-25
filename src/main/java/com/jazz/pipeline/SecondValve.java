package com.jazz.pipeline;

/**
 *
 * 第二个阀门，将传入的字符串中”11”替换成”22”
 * Created by XUJING592 on 2018/1/25.
 */
public class SecondValve implements Valve {
    protected Valve next = null;
    public Valve getNext() {
        return next;
    }
    public void invoke(String handling) {
        handling = handling.replaceAll("11", "22");
        System.out.println("Second阀门处理完后：" + handling);
        getNext().invoke(handling);
    }
    public void setNext(Valve valve) {
        this.next = valve;
    }
}