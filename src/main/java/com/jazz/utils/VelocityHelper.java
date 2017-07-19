package com.jazz.utils;


import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.context.Context;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:velocity工具类
 * @Team: 新金融业务研发团队
 * @Author BK
 * @Date 2017/7/7 14:52
 * @Version V2.0
 */

public class VelocityHelper {
    /** 单态实例 */
    private static final VelocityHelper instance = new VelocityHelper();

    /** 私有构造函数 */
    private VelocityHelper() {
        //初始化velocity的信息 主要设置一些Velocity的默认属性
        //初始化
        try {
            Velocity.init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * <pre>
     * 取得实例
     * </pre>
     */
    public static VelocityHelper getInstance() {
        return instance;
    }

    /**
     * 渲染：从reader到writer
     * @param context
     * @param writer
     * @param reader
     * @return
     */
    public boolean evaluate(Context context, Writer writer, Reader reader) {
        try {
            return Velocity.evaluate(context, writer, "", reader);
        } catch (Exception e) {
            throw new RuntimeException("velocity evaluate error! detail [" + e.getMessage() + "]");
        }
    }

    /**
     * 通过Map过滤一个输入流
     * @param map
     * @param reader
     * @return
     */
    @SuppressWarnings("unchecked")
    public InputStream evaluate(Map map, Reader reader) {
        try {
            // 把产生的输出流(字符流)，转换成输入流(字节流)
            byte[] dataBytes = this.evaluateToWriter(map, reader).toString().getBytes();
            return new ByteArrayInputStream(dataBytes);
        } catch (Exception e) {
            throw new RuntimeException("velocity evaluate error! detial [" + e.getMessage() + "]");
        }
    }

    /**
     * 通过Map过滤一个输入流
     * @param map
     * @param reader
     * @return
     */
    @SuppressWarnings("unchecked")
    public Writer evaluateToWriter(Map map, Reader reader) {
        try {
            VelocityContext context = convertVelocityContext(map);
            CharArrayWriter writer = new CharArrayWriter();
            //开始评估
            this.evaluate(context, writer, reader);

            return writer;
        } catch (Exception e) {
            throw new RuntimeException("velocity evaluate error! detail [" + e.getMessage() + "]");
        }
    }

    /**
     * 取得Velocity系统属性
     * @param key
     * @return
     */
    public Object getProperty(String key) {
        return Velocity.getProperty(key);
    }

    /**
     * 把Map转换成Context
     */
    private VelocityContext convertVelocityContext(Map<String, Object> map) {
        VelocityContext context = new VelocityContext();
        if (map == null) {
            return context;
        }
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            context.put(entry.getKey(), entry.getValue());
        }
        return context;
    }


    /**
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("date", "2010-04-27");
        map.put("weather", "晴朗");

        Reader reader = new FileReader("D:/Tmp/Src.txt");
        Writer writer = VelocityHelper.getInstance().evaluateToWriter(map, reader);

        // 今天是：2010-04-27，天气：晴朗！
        System.out.println(writer.toString());
    }
}