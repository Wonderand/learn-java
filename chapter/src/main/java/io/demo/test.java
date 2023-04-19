package io.demo;

import java.io.*;

/**
 * 字节流和字符流的使用场景
 *      字节流：适合处理所有类型的文件，包括图片、视频、音频、文本等
 *      字符流：适合处理文本文件，不适合处理图片、视频、音频等
 */
public class test {

    public static void main(String[] args) {

        // 1. 创建数据源文件对象
        File file = new File("E:\\jdk17\\test\\chapter\\");
        // 2. 创建目的地
        File file1 = new File("E:\\jdk17\\test\\demofile\\");
        // 3. 调用方法开始复制
        copyFile(file, file1);

    }

    private static void copyFile(File file, File file1) {
        // 1. 判断file是否是文件夹
        if (file.isDirectory()) {
            // 2. 如果是文件夹，创建一个同名的文件夹
            File newFile = new File(file1, file.getName());
            newFile.mkdir();
            // 3. 获取file文件夹下的所有文件
            File[] files = file.listFiles();
            // 4. 遍历files数组
            for (File file2 : files) {
                System.out.println(file2.getName());
                // 5. 递归调用copyFile方法
                copyFile(file2, newFile);
            }
        } else {
            // 6. 如果是文件，调用复制文件的方法
            try {
                copyFile1(file, file1);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static void copyFile1(File file, File dest) throws IOException {
        // 1. 创建字节输入流对象
        FileInputStream fis;
        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        // 2. 创建字节输出流对象
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(new File(dest, file.getName()));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        // 3. 创建字节数组
        byte[] bytes = new byte[1024];
        // 4. 循环读取
        while ((fis.read(bytes)) != -1) {
            // 5. 写出数据
            fos.write(bytes);
        }
        // 6. 释放资源
        fos.close();
        fis.close();
    }
}
