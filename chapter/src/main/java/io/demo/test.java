package io.demo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

//加密文件和解密文件
public class test {

    public static void main(String[] args) throws IOException {
//        encryption();
        decrypt();
    }

    public static void encryption() throws IOException{
        //1. 创建数据源文件对象
        FileInputStream fis = new FileInputStream("1.jpg");
        //2. 创建目的地
        FileOutputStream fos = new FileOutputStream("demofile\\1.jpg");
        //3. 调用方法开始复制并加密
        byte[] bytes = new byte[1024];
        int b;
        while ((b = fis.read())!=-1){
            fos.write(b^123);
        }

        fos.close();
        fis.close();
    }
    //解密文件
    public static void decrypt() throws IOException{

        //1. 创建数据源文件对象
        FileInputStream fis = new FileInputStream("demofile\\1.jpg");
        //2. 创建目的地
        FileOutputStream fos = new FileOutputStream("demofile\\decr.jpg");
        //3. 调用方法开始复制并加密
//        byte[] bytes = new byte[1024];
        int b;
        while ((b = fis.read())!=-1){
            fos.write(b^123);
        }

        fos.close();
        fis.close();
    }
}
