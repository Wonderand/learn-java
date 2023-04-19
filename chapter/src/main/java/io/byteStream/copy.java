package io.byteStream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class copy {
    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;

        try {
            fileInputStream = new FileInputStream("C:\\Users\\22906\\Videos\\2022.mp4");//39,976,097
            fileOutputStream = new FileOutputStream("c.mp4");
            //一次读取10240个字节
            byte[] bytes = new byte[10240];
            long size = fileInputStream.getChannel().size() / 10240;
            for (long i = 0; i < size + 1; i++) {
                fileInputStream.read(bytes);
                fileOutputStream.write(bytes, 0, bytes.length);
            }
/*
        String str = new String(bytes);
        fileOutputStream.write(str.getBytes());
一次读取一个
        int index;
        while ((index= fileInputStream.read(bytes))!=-1){
            fileOutputStream.write(index);
        }
*/
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            //先开后关
            fileOutputStream.close();
            fileInputStream.close();
        }

    }
}
