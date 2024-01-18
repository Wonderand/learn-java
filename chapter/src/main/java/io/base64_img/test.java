package io.base64_img;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.UUID;

public class test {

    public static void main(String[] args) {
        String s = base64Img("D:\\yitizi\\yitizi\\sword\\sworda\\sa00001\\001.jpg");
        System.out.println(s);
//        try (FileOutputStream fileOutputStream = new FileOutputStream("E:\\jdk17\\test\\chapter\\src\\main\\resources\\1.txt")){
//            //头部加入data:image/png;base64,
//            //FileUtils.writeStringToFile(new File("E:\\jdk17\\test\\chapter\\src\\main\\resources\\1.jpg"),s,"UTF-8");
//
//            //s = "data:image/png;base64," + s;
//            fileOutputStream.write(s.getBytes(),0,s.getBytes().length);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        String file ="E:\\jdk17\\test\\chapter\\src\\main\\resources\\1.txt";
//        base64ToImg(file,"E:\\jdk17\\test\\chapter\\src\\main\\resources\\2.jpg");
    }

    //读取文件夹下的所有jpg文件的路径
    @Test
    public void test1() throws IOException {
        //创建字符缓冲输出流对象
        BufferedWriter bw = new BufferedWriter(new FileWriter("E:\\jdk17\\test\\chapter\\src\\main\\file\\path.txt", true));
//        //写数据
//        bw.write("123");
//        bw.newLine();
//        bw.write("456");
//        //释放资源
//        bw.close();
        File file = new File("D:\\yitizi\\yitizi\\sword");
        ArrayList<String> jpgFiles = new ArrayList<>();
        getJpgFiles(file.getAbsolutePath(),jpgFiles);
        for (int i = 0; i < jpgFiles.size(); i++) {
//            System.out.println(jpgFiles.get(i));
            bw.write(jpgFiles.get(i));
            bw.newLine();
        }
        bw.close();
    }

    public static void getJpgFiles(String path, ArrayList<String> jpgFiles) {
        File folder = new File(path);
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    getJpgFiles(file.getAbsolutePath(), jpgFiles);
                } else {
                    String fileName = file.getName();
                    if (fileName.endsWith(".jpg")) {
                        jpgFiles.add(file.getAbsolutePath());
                    }
                }
            }
        }
    }

    //
    public static String base64Img(String filePath){
        File file = new File(filePath);

        try(FileInputStream fileInputStream = new FileInputStream(file)) {
            byte[] bytes = new byte[(int) file.length()];
            fileInputStream.read(bytes);
            String img = Base64.getEncoder().encodeToString(bytes).toString();
            return img;
        }catch (IOException e){
            e.printStackTrace();
        }
        return "";
    }

    //base64转图片
    public static void base64ToImg(String bast64filePath,String imgPath){
        // 解密
        try {
            // 读取base64image文件
            String str = FileUtils.readFileToString(new File(bast64filePath), "UTF-8");
            // 去掉base64前缀 data:image/jpeg;base64,
            //String s = "data:image/jpeg;base64,";
            //String imgbase64 = str.substring(s.length(),str.length());
            //FileUtils.writeStringToFile(new File("E:\\jdk17\\test\\chapter\\src\\main\\resources\\2.txt"), "\n"+imgbase64, "UTF-8");

            // 解密，解密的结果是一个byte数组
            System.out.println(str);
            byte[] imgbytes = Base64.getDecoder().decode(str);
//            // 转换成图片字节
////            for (int i = 0; i < imgbytes.length; ++i) {
////                if (imgbytes[i] < 0) {
////                    imgbytes[i] += 256;
////                }
////            }
//            // 生成图片
            OutputStream out = new FileOutputStream(imgPath);
            out.write(imgbytes);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
