package com.jazz.pipeline;

/**
 *  管道接口
 * Created by XUJING592 on 2018/1/25.
 */
public interface  Pipeline {
    public Valve getFirst();
    public Valve getBasic();
    public void setBasic(Valve valve);
    public void addValve(Valve valve);
}
