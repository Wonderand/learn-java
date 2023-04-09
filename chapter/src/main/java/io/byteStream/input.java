package io.byteStream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class input {

    public static void main(String[] args) throws IOException {

        FileInputStream fileInputStream = new FileInputStream("b.txt");
//        FileOutputStream fileOutputStream = new FileOutputStream("c.txt");

//        byte[] bytes = new byte[1024];
        fileInputStream.read();
//        fileOutputStream.write(bytes);
//        String s = new String(bytes);
//        System.out.println(s);
        int len;
        while ((len = fileInputStream.read())!=-1){
            System.out.println((char) len);
        }

//        fileOutputStream.close();
        fileInputStream.close();
    }
}
