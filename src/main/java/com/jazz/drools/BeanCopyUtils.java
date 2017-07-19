package com.jazz.drools;

import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.beans.BeanCopier;
import net.sf.cglib.core.Converter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description:  bean拷贝工具类
 */
@Slf4j
public class BeanCopyUtils {
    
    private static Map<String, BeanCopier> beanCopierMap = new ConcurrentHashMap<String, BeanCopier>();
    
    /**
     * 两个类之前互相拷贝相同的属性
     * @param source
     * @param target
     * @param <T>
     * @return
     */
    public static  <T> T copy(Object source,Class<T> target ){
        Object t  = null;
        try {
            t =  Class.forName(target.getName()).newInstance();
            BeanCopier  bc =BeanCopier.create(source.getClass(),target,false);
            bc.copy(source,t,null);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return (T)t;
    }
    
    /**
     * 两个类之前互相拷贝相同的属性，需要特殊处理的属性可以使用Converter接口来自定义转换器
     * @param source 源对象
     * @param target 目标对象
     * @param converter 自定义转换器
     */
    public static void copyProperties(Object source, Object target, Converter converter) {
        boolean needConverter = false;
        if (converter != null) {
            needConverter = true;
        }
        BeanCopier copier = generateCopier(source, target, needConverter);
        copier.copy(source, target, converter);
    }
    
    private static BeanCopier generateCopier(Object source, Object target, boolean needConverter) {
        String beanKey = generateKey(source.getClass(), target.getClass());
        if (!beanCopierMap.containsKey(beanKey)) {
            BeanCopier copier = BeanCopier.create(source.getClass(), target.getClass(), needConverter);
            beanCopierMap.put(beanKey, copier);
            return copier;
        } else {
            return beanCopierMap.get(beanKey);
        }
    }
    
    private static String generateKey(Class<? extends Object> class1, Class<? extends Object> class2) {
        return class1.toString() + class2.toString();
    }
    
}
