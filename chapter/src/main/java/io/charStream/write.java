package io.charStream;

import java.io.FileWriter;
import java.io.IOException;

public class write {

    public static void main(String[] args) throws IOException {

        //续写开关，在原来文件的基础上追加
        FileWriter fileWriter = new FileWriter("d.txt",true);
//        fileWriter.write("\n你好吗！！！111dd");
        char[] c = new char[8192];

        System.out.println(c);
        for (int i = 0; i < 8192; i++) {
            c[i] = 'a';
//            fileWriter.write(c);
        }
        fileWriter.write(c);
        System.out.println(c[3]);
        fileWriter.close();
    }
}
