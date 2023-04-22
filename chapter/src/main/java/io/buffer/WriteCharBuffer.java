package io.buffer;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WriteCharBuffer {
    public static void main(String[] args) throws IOException {

        //创建字符缓冲输出流对象
        BufferedWriter bw = new BufferedWriter(new FileWriter("demofile\\b.txt", true));
        //写数据
        bw.write("123");
        bw.newLine();
        bw.write("456");
        //释放资源
        bw.close();
    }
}
