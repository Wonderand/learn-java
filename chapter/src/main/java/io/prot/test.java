package io.prot;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.Properties;

/**
 * Properties是唯一一个可以和IO流结合使用的集合类
 */
public class test {
    public static void main(String[] args) throws Exception{
        wrfile();
    }
    /**
     * Properties集合的store方法
     *  传递任意的字节或者字符输入流
     *  流对象读取文件中的键值对,保存到集合
     * @throws Exception
     */
    public static void refile() throws Exception{
        FileReader fr = new FileReader("E:\\jdk17\\test\\chapter\\src\\main\\resources\\a.txt");
        Properties pro = new Properties();
        pro.load(fr);
        fr.close();
        System.out.println(pro);
    }
    /**
     * Properties集合的store方法
     *  传递任意的字节或者字符输出流
     *  集合中的键值对,写入到文件中保存
     * @throws Exception
     */
    public static void wrfile() throws Exception{
        Properties pro = new Properties();
        pro.setProperty("name","zhangsan");
        pro.setProperty("age","18");
        pro.setProperty("email","1234567@163.com");
        FileWriter fw = new FileWriter("E:\\jdk17\\test\\chapter\\src\\main\\resources\\pro.txt");
        pro.store(fw,"save data");
        fw.close();
    }
}
