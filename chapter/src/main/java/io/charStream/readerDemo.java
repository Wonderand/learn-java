package io.charStream;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class readerDemo {

    public static void main(String[] args) throws IOException {

        FileReader fileReader = new FileReader("d.txt");
        fileReader.read();
        FileWriter fileWriter = new FileWriter("d.txt");

        int len;
        while ((len = fileReader.read())!=-1){
            System.out.println(len);
        }

        fileWriter.close();
        fileReader.close();
    }
}
