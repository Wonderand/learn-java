package reflection.class_;

import java.io.Serializable;

/**
 * 哪些类型有Class对象
 *
 * 反射是动态加载的，只有在动态加载时才知道要加载哪个类，如果运行时不用该类，就不会加载该类。
 * 编译阶段即使没有该类也不会报错
 *
 * 直接new一个对象，是静态加载类，编译时就要知道要加载哪个类
 */
public class AllTypeClass {

    public static void main(String[] args) {

        Class<String> stringClass = String.class; //外部类
        Class<Serializable> serializableClass = Serializable.class; //接口
        Class<Integer[]> aClass = Integer[].class; //数组
        Class<float[][]> aClass1 = float[][].class; //二维数组
        Class<Deprecated> deprecatedClass = Deprecated.class; //注解
        Class<Thread.State> stateClass = Thread.State.class; //枚举
        Class<Long> longClass = long.class; //基本数据类型
        Class<Void> voidClass = void.class; //void
        Class<Class> classClass = Class.class; //Class

        System.out.println(stringClass+"\n "+
                serializableClass+"\n "+
                aClass+"\n "+
                aClass1+"\n " +
                deprecatedClass+"\n "+
                stateClass+"\n "+
                longClass+"\n "+
                voidClass+"\n "+
                classClass);
    }
}
