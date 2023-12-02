package io.nio;import java.io.FileNotFoundException;import java.io.RandomAccessFile;import java.nio.ByteBuffer;import java.nio.channels.FileChannel;import java.util.RandomAccess;/** * @Classname FileChannelDemo2 * @Author: hzr * @Description TODO * @Version 1.0.0 * @Date 2023/12/2 12:01 * @Created by 22906 */// filechanne写操作public class FileChannelDemo2 {    public static void main(String[] args) throws Exception {        //打开FIleChannel        RandomAccessFile aFile = new RandomAccessFile("E:\\jdk17\\test\\chapter\\src\\main\\file\\nio-data.txt","rw");        FileChannel channel = aFile.getChannel();        //创建buffer        ByteBuffer buffer = ByteBuffer.allocate(1024);        String newDate = "胡志荣";        buffer.clear();        // 写入内容        ByteBuffer put = buffer.put(newDate.getBytes());        buffer.flip();        //FIleChannel完成最终的实现        while (buffer.hasRemaining()){            channel.write(buffer);        }        //关闭        channel.close();        aFile.close();    }}