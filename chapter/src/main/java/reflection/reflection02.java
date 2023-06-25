package reflection;

import reflection.pojo.Cat;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @version 1.0
 * 测试反射调用的性能和优化方案
 * @Author: huzhirong
 */
public class reflection02  {
    public static void main(String[] args) throws Exception {
        //反射优化方案——关闭访问检查
        //Field
        //Method
        //Constructor

        reflection02.m1();
        reflection02.m3();
    }

    //传统方式调用方法hi
    public static void m1() {
        Cat cat = new Cat();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 90000000; i++) {
            cat.hi();
        }
        long end = System.currentTimeMillis();
        System.out.println("m1()方法调用hi方法 耗时：" + (end - start));
    }

    //反射方式调用方法hi
    public static void m2() throws Exception {
        Class<?> aClass = Class.forName("reflection.pojo.Cat");

        Object o = aClass.newInstance();
        Method hi = aClass.getMethod("hi");
        long start = System.currentTimeMillis();
        for (int i = 0; i < 90000000; i++) {
            hi.invoke(o);
        }
        long end = System.currentTimeMillis();
        System.out.println("m2()方法调用hi方法 耗时：" + (end - start));
    }

    //反射优化方案——关闭访问检查
    public static void m3() throws Exception {
        Class<?> aClass = Class.forName("reflection.pojo.Cat");

        Object o = aClass.newInstance();
        Method hi = aClass.getMethod("hi");
        //取消在反射调用方法时的访问检查
        hi.setAccessible(true);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 90000000; i++) {
            hi.invoke(o);
        }
        long end = System.currentTimeMillis();
        System.out.println("m2()方法调用hi方法 耗时：" + (end - start));
    }
}
