package io.nio;import java.io.FileNotFoundException;import java.io.IOException;import java.io.RandomAccessFile;import java.nio.ByteBuffer;import java.nio.channels.ByteChannel;import java.nio.channels.FileChannel;/** * @Classname FileChannelDemo1 * @Author: hzr * @Description TODO * @Version 1.0.0 * @Date 2023/12/2 11:47 * @Created by 22906 */// filechanne读操作public class FileChannelDemo1 {    public static void main(String[] args) throws IOException {        //创建filechannel        RandomAccessFile aFile = new RandomAccessFile("E:\\jdk17\\test\\chapter\\src\\main\\file\\nio-data.txt", "rw");        FileChannel channel = aFile.getChannel();        //创建buffer        ByteBuffer buffer = ByteBuffer.allocate(1024);        //读取文件到buffer中        int read = channel.read(buffer);        while (read != -1){            System.out.println("读取了:"+read);            buffer.flip();            while (buffer.hasRemaining()){                System.out.println((char) buffer.get());            }            buffer.clear();            read = channel.read(buffer);        }        aFile.close();        System.out.println("结束了");    }}