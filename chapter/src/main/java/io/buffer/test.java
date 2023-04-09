package io.buffer;

import java.io.FileInputStream;
import java.io.IOException;

public class test {

    public static void main(String[] args) throws IOException {

        FileInputStream fileInputStream = new FileInputStream("a.txt");

        byte[] bytes = new byte[1024];
        fileInputStream.read(bytes);
        String s = new String(bytes);
        System.out.println(s);
        int len;
        while ((len = fileInputStream.read())!=-1){
            System.out.println((char) len);
        }

        fileInputStream.close();
    }
}
