package io.demo;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

//对文件中的数据进行排序
public class test1 {

    public static void main(String[] args) throws IOException {

        //1.读取文件中的数据
        FileReader fileReader = new FileReader("d.txt");
        StringBuilder builder = new StringBuilder();
        int len;
        while ((len = fileReader.read())!=-1){
            builder.append((char) len);
        }
        fileReader.close();
        System.out.println(builder.toString());
        //2.排序
        ArrayList<Integer> list = new ArrayList<>();
        String string = builder.toString();
        String[] arrStr = string.split("-");
        for (String s : arrStr) {
            int i = Integer.parseInt(s);
            list.add(i);
        }
        Collections.sort(list);
        System.out.println(list);
        //3.写入文件
        FileWriter fileWriter = new FileWriter("e.txt");
        for (int i = 0; i < list.size(); i++) {
            if (i==list.size()-1){
                fileWriter.write(list.get(i)+"");
            }else {
                fileWriter.write(list.get(i)+"-");
            }
        }
        fileWriter.close();
    }
}
