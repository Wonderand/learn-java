package io.buffer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadCharBuffer {

    public static void main(String[] args) throws IOException {

        //1.创建字符缓冲输入流对象
        BufferedReader br = new BufferedReader(new FileReader("demofile//a.txt"));
        //读取数据
//        String s = br.readLine();
//        System.out.println(s);
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        //释放资源
        br.close();

    }
}