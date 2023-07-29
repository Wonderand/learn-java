package reflection.class_;

import reflection.pojo.Car;

/**
 *  演示得到class对象的各种格式
 */
public class GetClass {
    public static void main(String[] args) throws ClassNotFoundException {

        //1.通过读取配置文件获取
        String classAllPath = "reflection.pojo.Car";
        Class<?> aClass = Class.forName(classAllPath);
        System.out.println(aClass);

        //2.通过类名.class获取 应用场景：多用于参数传递
        Class<Car> aClass1 = Car.class;
        System.out.println(aClass1);

        //3.通过对象.getClass()获取 应用场景：多用于有对象实例的情况
        Car car = new Car();
        Class<? extends Car> aClass2 = car.getClass();
        System.out.println(aClass2);

        //4.通过类加载器{4种}获取Class对象
        //(1)先得到类加载器 car
        ClassLoader classLoader = GetClass.class.getClassLoader();
        //(2)通过类加载器得到Class对象
        Class<?> aClass3 = classLoader.loadClass(classAllPath);
        System.out.println(aClass3);

        // aClass1、aClass2、aClass3都是同一个Class对象,因为类在只在内从中加载一次

        // 5.基本数据类型（int、char、boolean、byte、short、long、float、double）按照如下方式获取Class对象
        Class<Integer> integerClass = int.class;
        Class<Character> characterClass = char.class;
        Class<Boolean> booleanClass = boolean.class;
        Class<Byte> byteClass = byte.class;
        Class<Short> shortClass = short.class;
        Class<Long> longClass = long.class;
        Class<Float> floatClass = float.class;
        Class<Double> doubleClass = double.class;
        System.out.println(integerClass);
        System.out.println(characterClass);
        System.out.println(booleanClass);
        System.out.println(byteClass);
        System.out.println(shortClass);
        System.out.println(longClass);
        System.out.println(floatClass);
        System.out.println(doubleClass);
        // 基本数据类型的包装类可以通过.TYPE获取Class对象
        Class<Integer> type = Integer.TYPE;
        Class<Character> type1 = Character.TYPE;
        System.out.println(type);
        System.out.println(type1.hashCode());
        System.out.println(characterClass.hashCode());
    }
}
