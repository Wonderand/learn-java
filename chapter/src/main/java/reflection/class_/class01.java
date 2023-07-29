package reflection.class_;

import reflection.pojo.Cat;

public class class01 {
    public static void main(String[] args) {
        //1.通过类名.class获取
        try {
            Cat cat = new Cat();
            //对于某个类的class对象，在内存中只有一份，因为类只加载一次
            Class<?> aClass = Class.forName("reflection.pojo.Cat");
            Class<?> aClass1 = Class.forName("reflection.pojo.Cat");
            System.out.println(cat.getClass().hashCode());
            System.out.println(aClass.hashCode());
            System.out.println(aClass1.hashCode());
            Class<?> aClass2 = Class.forName("reflection.pojo.Dog");
            System.out.println(aClass2.hashCode());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
