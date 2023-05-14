package reflection;

import io.objectStream.PropertiesTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Properties;

public class test {

    public static void main(String[] args) {

        //根据配置文件创建对象并调用方法

        //用io流读取配置文件
        FileReader fr = null;
        try {
            fr = new FileReader("E:\\jdk17\\test\\chapter\\src\\main\\resources\\re.properties");
            PropertiesTest test = new PropertiesTest();
            Properties refile = test.refile(fr);
            //获取配置文件中的数据
            String cl = refile.getProperty("classfullpath").toString();
            String md = refile.getProperty("method").toString();
            System.out.println(cl);
            //加载该类进内存
            Class<?> aClass = Class.forName(cl);
            System.out.println(aClass);
            Object invoke = aClass.getMethod(md).invoke(aClass.newInstance());
            System.out.println(invoke);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
