package io.utilsIO;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class test {
    public static void main(String[] args) throws Exception {

        // 读取文件到字符串中
        String content = FileUtils.readFileToString(new File("E:\\jdk17\\test\\chapter\\src\\main\\resources\\a.txt"), "UTF-8");
        System.out.println(content);

        // 从 URL 下载文件到本地文件系统中
//        FileUtils.copyURLToFile(new URL("http://commons.apache.org"), new File("commons.html"));

//        // 将字符串写入到文件中
//        FileUtils.writeStringToFile(new File("file.txt"), "Hello World!", "UTF-8");
//
//        // 将字节数组写入到文件中
//        byte[] bytes = "Hello World!".getBytes();
//        FileUtils.writeByteArrayToFile(new File("file.txt"), bytes);

        // 将输入流写入到文件中
        //IOUtils.copy(inputStream, new FileOutputStream("file.txt"));
    }
}
