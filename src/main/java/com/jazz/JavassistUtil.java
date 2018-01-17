package com.jazz;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.pingan.haofang.riskcontrol.dto.droolscall.BaseDroolsMessage;
import com.pingan.haofang.riskcontrol.dto.droolscall.BeanSetterMethodInvoker;
import javassist.*;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.kie.internal.builder.KnowledgeBuilder;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * @author BK
 * @version V2.0
 * @description:Javassist工具类
 * @date 2018/01/15 11:43
 */
public class JavassistUtil {
    private static final Map<String, Class<?>> CLASS_MAP_CACHE = Maps.newConcurrentMap();
    private static final Map<String, CtClass> CTLASS_MAP_CACHE = Maps.newConcurrentMap();
    public static final String DYNAMIC_MESSAGE_PREFIX="com.bk.message.";
    public static Class<?> generate(String className, List<Meta> metaList) throws NotFoundException, CannotCompileException, IOException, ClassNotFoundException {
        Class<?> clazz = CLASS_MAP_CACHE.get(className);
        if (clazz == null) {
            CtClass ctClass =  getCtClass(className,metaList);
            clazz = ctClass.toClass();
            CLASS_MAP_CACHE.put(className,clazz);
            saveClass(ctClass);
        }
        return clazz;
    }
    public static CtClass getCtClass(String className, List<Meta> metaList) throws NotFoundException, CannotCompileException, IOException, ClassNotFoundException {
        CtClass ctClass = CTLASS_MAP_CACHE.get(className);
        if(ctClass ==null){
            ClassPool classPool = ClassPool.getDefault();
            ctClass = classPool.makeClass(className);
            ClassClassPath classPath = new ClassClassPath(KnowledgeBuilder.class);
            classPool.insertClassPath(classPath);
//            setInterface(classPool, ctClass);
            setExtendClass(classPool, ctClass);
            addFields(metaList, classPool, ctClass);
            addNoArgsConstructor(ctClass);
        }
        CTLASS_MAP_CACHE.put(className, ctClass);
        return ctClass;
    }

    /**
     * 获取CtClass
     *
     * @param clazz
     * @return
     * @throws NotFoundException
     */
    private static CtClass resolveCtClass(Class<?> clazz) throws NotFoundException {
        ClassPool clzPool = ClassPool.getDefault();
        return clzPool.get(clazz.getName());
    }

    private static CtClass getParentClass() throws NotFoundException {
        return ClassPool.getDefault().get(BaseDroolsMessage.class.getName());
    }

    private static String convertFirstUpper(String sourceString) {
        return StringUtils.upperCase(sourceString.substring(0, 1)) + sourceString.substring(1);
    }

    private static void setExtendClass(ClassPool classPool, CtClass ctClass) throws CannotCompileException, NotFoundException {
        ctClass.setSuperclass(classPool.get(BaseDroolsMessage.class.getName()));
    }

    private static void setInterface(ClassPool classPool, CtClass ctClass) throws NotFoundException {
        ctClass.setInterfaces(new CtClass[]{classPool.get(Runnable.class.getName())});
    }

    /**
     * 保存生成的字节码
     *
     * @param ctClass
     * @throws CannotCompileException
     * @throws IOException
     */
    private static void saveClass(CtClass ctClass) throws CannotCompileException, IOException {
        ctClass.writeFile("d://temp");
        ctClass.defrost();
    }

    /**
     * 为类设置方法
     * @param ctClass
     * @throws CannotCompileException
     */
    private static void addVoidMethod(CtClass ctClass, String methodName) throws CannotCompileException {
//        CtMethod method = new CtMethod(CtClass.voidType, "getProperty", new CtClass[]{new }, ctClass);
//        method.setModifiers(Modifier.PUBLIC);
//        method.setBody("{com.pingan.haofang.riskcontrol.dto.droolscall.BeanSetterMethodInvoker.getter(this,\""+property+"\")}");
//        ctClass.addVoidMethod(method);
        CtMethod showInfo = CtMethod.make("private void "+methodName+"(String property) {}", ctClass);
        showInfo.setBody("{return com.pingan.haofang.riskcontrol.dto.droolscall.BeanSetterMethodInvoker.getter(this,$1);}");
        ctClass.addMethod(showInfo);
    }

    /**
     * 执行生成的类
     *
     * @param clazz
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     */
    private static void invokeMethod(Class<?> clazz) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Object obj = clazz.newInstance();
        clazz.getMethod("setValue", String.class).invoke(obj, "hello");
        clazz.getMethod("run").invoke(obj);
        obj = clazz.getConstructor(String.class).newInstance("OK");
        clazz.getMethod("run").invoke(obj);
    }
    private static void addField(ClassPool classPool, CtClass ctClass) throws CannotCompileException, NotFoundException {
        CtField field = new CtField(classPool.get(String.class.getName()), "value", ctClass);
        field.setModifiers(Modifier.PRIVATE);
        // 添加getter和setter方法
        ctClass.addMethod(CtNewMethod.setter("setValue", field));
        ctClass.addMethod(CtNewMethod.getter("getValue", field));
        ctClass.addField(field);
    }

    private static void addAllArgsConstructor(ClassPool classPool, CtClass ctClass) throws NotFoundException, CannotCompileException {
        // 参数构造器
        CtConstructor constructor = new CtConstructor(null, ctClass);
        constructor = new CtConstructor(new CtClass[]{classPool.get(String.class.getName())}, ctClass);
        constructor.setModifiers(Modifier.PUBLIC);
        constructor.setBody("{this.value=$1;}");
        ctClass.addConstructor(constructor);
    }

    /**
     * 为类型设置字段
     *
     * @param metaList
     * @param classPool
     * @param ctClass
     * @throws CannotCompileException
     * @throws NotFoundException
     */
    private static void addFields(List<Meta> metaList, ClassPool classPool, CtClass ctClass) throws CannotCompileException, NotFoundException {
        for (Meta meta : metaList) {
            CtField ctField = new CtField(classPool.getCtClass(meta.getType()), meta.getFieldName(), ctClass);
            ctField.setModifiers(Modifier.PRIVATE);
            ctClass.addField(ctField);
            ctClass.addMethod(CtNewMethod.getter("get" + convertFirstUpper(meta.getFieldName()), ctField));
            ctClass.addMethod(CtNewMethod.setter("set" + convertFirstUpper(meta.getFieldName()), ctField));
        }
    }

    /**
     * 无参构造器
     *
     * @param ctClass
     * @throws CannotCompileException
     */
    private static void addNoArgsConstructor(CtClass ctClass) throws CannotCompileException {
        CtConstructor constructor = new CtConstructor(null, ctClass);
        constructor.setModifiers(Modifier.PUBLIC);
        constructor.setBody("{}");
        ctClass.addConstructor(constructor);
    }

    @Data
    public static class Meta {
        private String type;
        private String fieldName;

        public Meta(String type, String fieldName) {
            this.type = type;
            this.fieldName = fieldName;
        }
    }
    public static String getClassName(String className){
        return DYNAMIC_MESSAGE_PREFIX+className;
    }

    public static Object getDynamicMessage(String className, List<Meta> metas) {
        Class<?> clazz = null;
        try {
            clazz = generate(className,metas);
        } catch (NotFoundException e) {
            e.printStackTrace();
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (clazz != null) {
            try {
                return clazz.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    public static void main(String[] args) throws Exception {
        List<Meta> metaList = Lists.newArrayList(new Meta("java.lang.String", "applierName"), new Meta("java.lang.String", "applierMobile"), new Meta("java.lang.Long", "maritalStatus"), new Meta("java.lang.Long", "highestDegree"));
        Object res = getDynamicMessage("com.bk.Test",metaList);
        BeanSetterMethodInvoker.setter(res , "applierName","xxxx");
        System.out.println(BeanSetterMethodInvoker.getter(res,"applierName"));
    }

}
