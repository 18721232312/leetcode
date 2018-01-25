package com.jazz.pipeline;

/**
 * Created by XUJING592 on 2018/1/25.
 */
public class Main {
    public static void main(String[] args) {
        String handling="aabb1122zzyy";
        BasicValve basicValve = new BasicValve();
        StandardPipeline pipeline = new StandardPipeline();
        SecondValve secondValve = new SecondValve();
        ThirdValve thirdValve = new ThirdValve();
        pipeline.setBasic(basicValve);
        pipeline.addValve(secondValve);
        pipeline.addValve(thirdValve);
        pipeline.getFirst().invoke(handling);
    }
}