package io.utilsIO;

import java.io.File;
import java.io.FileInputStream;

import static io.utilsIO.Util.readFileToString;

public class demo {

    public static void main(String[] args) {

       inputStreamChina();
    }
    private static void inputStreamChina() {
        // TODO Auto-generated method stub
        File f=new File("E:\\jdk17\\test\\chapter\\src\\main\\resources\\c.txt");
        try (FileInputStream fis=new FileInputStream(f)){
            byte[] bytes=new byte[(int) f.length()];
            fis.read(bytes);
//			window记事本默认编码方式是gbk,如果保存文件以UTF-8保存，便以UTF-8读出
//			String str = new String(bytes,"GBK");
            String str = new String(bytes,"UTF-8");
//            str.replaceAll("几个", "五把");
            for (int i = 0; i < str.length(); i++) {
//                if (str.charAt(i)=='几'){
//                    str.replace("", "五把");
//                }
                str = str.replace("几", "五");
                System.out.println(str.charAt(i));
            }
            System.out.println(str);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

}
