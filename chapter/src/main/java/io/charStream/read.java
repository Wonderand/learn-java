package io.charStream;

import java.io.FileReader;
import java.io.IOException;

public class read {

    public static void main(String[] args) throws IOException {

        FileReader fileReader = new FileReader("a.txt");

        char[] chars = new char[2];
        int ch;
        while ((ch=fileReader.read(chars))!=-1){

            System.out.print(new String(chars,0,ch));
        }

        fileReader.close();
    }
}
