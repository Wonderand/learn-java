package io.charStream;

import java.io.FileWriter;
import java.io.IOException;

public class writeDemo {

    public static void main(String[] args) throws IOException {

        FileWriter fileWriter = new FileWriter("d.txt",true);
//        fileWriter.write("\n你好吗！！！111dd");
        char[] c = new char[8192];
        for (int i = 0; i < 8192; i++) {
            c[i] = 'a';
//            fileWriter.write(c);
        }
        fileWriter.write(c);
        System.out.println(c[3]);
        fileWriter.flush();//将缓冲区的数据存放到文件
        fileWriter.close();
    }

}
