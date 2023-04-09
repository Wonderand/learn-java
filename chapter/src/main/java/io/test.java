package io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class test {

    public static void main(String[] args) throws IOException {

        FileOutputStream fileOutputStream = new FileOutputStream("b.txt");

        String str = " git rm --cached -r filepath";


        //byte[] bytes = {97, 98, 99, 100, 101, 102, 103};

        fileOutputStream.write(str.getBytes());
        fileOutputStream.close();

        FileInputStream fileInputStream = new FileInputStream("a.txt");

        long size = fileInputStream.getChannel().size();

        int b;
        while ((b = fileInputStream.read()) != -1) {
            System.out.println((char) b);

        }
        fileInputStream.close();
//        System.out.println(size);


    }
}
