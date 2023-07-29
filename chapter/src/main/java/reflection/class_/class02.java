package reflection.class_;

import reflection.pojo.Car;

import java.lang.reflect.Field;
import java.util.logging.Logger;

//演示class类的常用方法
public class class02 {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException, NoSuchFieldException {
        String classAllPath = "reflection.pojo.Car";
        // 1.获取到car类对应的class对象
        // ？表示不确定的java类型
        try {
            Class<?> aClass = Class.forName(classAllPath);
            //2.输出class
            System.out.println(aClass); //显示aclass对象的全路径
            System.out.println(aClass.getClass()); //输出运行类型
            //3.得到包名
            System.out.println(aClass.getPackage());
            //4.得到全类名
            System.out.println(aClass.getName());
            //5.通过aClass创建对象
            Car car = (Car) aClass.newInstance();
            System.out.println(car);
            //6.通过反射获取属性 brand(只能获取公有属性)
            Field brand = aClass.getField("brand");
            System.out.println(brand.get(car));
            //7.通过反射给属性赋值
            brand.set(car, "奔驰");
            System.out.println(brand.get(car));
            //8.获取所有的属性
            Field[] fields = aClass.getFields();
            System.out.println("++++++++++++++所有的属性++++++++++++++");
            for (Field field : fields) {
                System.out.println(field.getName());//属性字段的名称
            }

        } catch (ClassNotFoundException e) {
            Logger.getLogger("class02").info("未找到该类");
            throw new RuntimeException(e);
        }
    }
}
