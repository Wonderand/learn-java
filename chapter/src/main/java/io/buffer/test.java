package io.buffer;


import java.io.*;

//缓冲流
public class test {
    public static void main(String[] args) throws IOException {
        //缓冲流对象
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream("a.txt"));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("demofile\\a.txt"));
        //读写数据
        int b;
        while ((b = bis.read()) != -1) {
            bos.write(b);
        }
        //释放资源
        bos.close();
        bis.close();

    }
}
