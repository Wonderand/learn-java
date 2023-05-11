package io.prot;

import io.prot.pojo.Person;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class writeAndreadObject {
    public static void main(String[] args) throws Exception {
        readobj();
        //wrobj();
    }

    /**
     * 序列化流ObjectOutputStream
     * @throws Exception
     */
    public static void wrobj() throws Exception{
        FileOutputStream fos = new FileOutputStream("E:\\jdk17\\test\\chapter\\src\\main\\resources\\person.txt");
        //创建写出对象的序列化流的对象,构造方法传递字节输出流
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        Person person = new Person("zhangsan", 18);
        //调用序列化流的方法writeObject,写出对象
        oos.writeObject(person);
        oos.close();
    }

    /**
     * 反序列化流ObjectInputStream
     * @throws ClassNotFoundException
     * @throws Exception
     */
    public static void readobj() throws ClassNotFoundException,Exception{
        FileInputStream fis = new FileInputStream("E:\\jdk17\\test\\chapter\\src\\main\\resources\\person.txt");
        //创建反序列化流的对象,构造方法传递字节输入流
        ObjectInputStream ois = new ObjectInputStream(fis);
        //调用反序列化流的方法readObject读取对象
        Object o = ois.readObject();
        System.out.println(o);
        ois.close();

    }

}
