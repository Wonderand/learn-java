package io.buffer;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class bufferedCopy {

    public static void main(String[] args) throws Exception {

        //缓冲流对象
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream("C:\\Users\\22906\\Videos\\2022.mp4"));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("demofile\\4.mp4"));
        //一次读写多个数据
        byte[] bys = new byte[1024];
        int len;
        while ((len = bis.read(bys)) != -1) {
            bos.write(bys, 0, len);
        }
        //释放资源
        bos.close();
        bis.close();
    }
}
