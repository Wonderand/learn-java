package io.utilsIO;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * Commons-io工具类的使用
 */
public class Util {

    /**
     * FileUtis工具类方法
     * static void copyDirectoryToDirectory(File src,File desc)
     * 复制文件夹
     */
    public static void copyDirectoryToDirectory() throws IOException {
        FileUtils.copyDirectoryToDirectory(new File("d:\\demo"), new File("c:\\"));
        System.out.println("FileUtils copyDirectoryToDirectory");
    }
    /**
     * FileUtis工具类方法
     * static void copyFile(File src,File desc)
     * 复制文件
     */
    public static void copyFile() throws IOException {
        FileUtils.copyFile(new File("d:\\demo\\a.txt"), new File("c:\\a.txt"));
        System.out.println("FileUtils copyFile");
    }
    /**
     * FileUtis工具类方法
     * static void writeStringToFile(File src,String date)
     * 将字符串直接写到文件中
     */
    public static boolean writeStringToFile(String toPath, String date) {
        Boolean isOk = true;
        try {
            FileUtils.writeStringToFile(new File(toPath), date);
        } catch (IOException e) {
            isOk = false;
            throw new RuntimeException(e);
        }
        System.out.println("FileUtils writeStringToFile");
        return isOk;
    }
    /**
     * FileUtis工具类方法
     * static String readFileToString(File src)
     * 读取文本,返回字符串
     */
    public static String readFileToString (String src){
        String data = null;
        try {
            data = FileUtils.readFileToString(new File(src));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        System.out.println(data);
//        System.out.println("FileUtils readFileToString");
        return data;
    }
    /**
     * FileUtis工具类方法
     * static List<String> readLines(File src)
     * 读取文本,返回字符串集合
     */
    public static void readLines() throws IOException {
        FileUtils.readLines(new File("d:\\demo\\a.txt"));
        System.out.println("FileUtils readLines");
    }
    /**
     * FileUtis工具类方法
     * static void writeLines(File src,Collection<String> lines)
     * 将字符串集合写入到文件中
     */
    public static void writeLines() throws IOException {
        FileUtils.writeLines(new File("d:\\demo\\a.txt"), FileUtils.readLines(new File("d:\\demo\\a.txt")));
        System.out.println("FileUtils writeLines");
    }
    /**
     * FileUtis工具类方法
     * static void forceDelete(File src)
     * 强制删除文件
     */
    public static void forceDelete() throws IOException {
        FileUtils.forceDelete(new File("d:\\demo\\a.txt"));
        System.out.println("FileUtils forceDelete");
    }

}
