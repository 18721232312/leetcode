package com.jazz.pipeline;

/**
 * 阀门接口
 * Created by XUJING592 on 2018/1/25.
 */
public interface Valve {
    public Valve getNext();
    public void setNext(Valve valve);
    public void invoke(String handling);
}
