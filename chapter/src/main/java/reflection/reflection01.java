package reflection;

import java.io.FileInputStream;
import java.io.FileReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Properties;


//反射的优点和缺点

/**
 * 优点：可以动态的创建和使用对象（也是框架底层的核心），使用灵活，解耦。没有的反射机制，框架技术就失去了底层支持
 * 缺点：反射的基本是解释执行，对执行速度有影响。反射的效率低，因为反射是动态的，所以编译器无法对其检查，容易出现安全问题
 */
public class reflection01 {

    public static void main(String[] args) throws Exception {

        //根据配置文件创建对象并调用方法
        Properties properties = new Properties();
        properties.load(new FileInputStream("E:\\jdk17\\test\\chapter\\src\\main\\resources\\re.properties"));
        String cl = properties.getProperty("classfullpath"); //reflection.pojo.Cat
        String methodName = properties.get("method").toString(); //eat

        //加载该类进内存
        Class<?> aClass = Class.forName(cl);

        //创建对象
        Object o = aClass.newInstance();
        //即：在反射中，可以把方法视为对象（万物皆对象）
        Method method = aClass.getMethod(methodName);
        //执行方法
        method.invoke(o);

        //获取成员变量
        //Field[] fields = aClass.getFields(); //只能获取public修饰的成员变量
        //getFields()方法只能获取public修饰的成员变量，而getDeclaredFields()方法可以获取所有的成员变量
        Field age = aClass.getField("age");
        System.out.println(age.get(o));

        //获取构造方法
        Constructor<?> constructor = aClass.getConstructor();//()中获取无参构造方法
        System.out.println(constructor);

        //获取有参构造方法
        Constructor<?> constructor1 = aClass.getConstructor(String.class);//String.calss是传入参数类型
        System.out.println(constructor1);
    }
}
