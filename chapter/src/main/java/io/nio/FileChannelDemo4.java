package io.nio;import java.io.RandomAccessFile;import java.nio.channels.FileChannel;/** * @Classname FileChannelDemo4 * @Author: hzr * @Description TODO * @Version 1.0.0 * @Date 2023/12/2 12:35 * @Created by 22906 *///通道之间的数据传输 transferTopublic class FileChannelDemo4 {    public static void main(String[] args) throws Exception{        //创建两个fileChannel        RandomAccessFile aFile = new RandomAccessFile("E:\\jdk17\\test\\chapter\\src\\main\\file\\nio-data3a.txt","rw");        FileChannel fromChannel = aFile.getChannel();        RandomAccessFile bFile = new RandomAccessFile("E:\\jdk17\\test\\chapter\\src\\main\\file\\nio-data3b.txt","rw");        FileChannel toChannel = bFile.getChannel();        //fromChannel传输到toChannel        long position = 0;        //传输截止位置//        long fSize = fromChannel.size();//        System.out.println(fSize);//        fromChannel.transferTo(position,fSize,toChannel);        //toChannel传输到fromChannel        long tSize = toChannel.size();        toChannel.transferTo(position,tSize,fromChannel);        aFile.close();        bFile.close();        System.out.println("over!");    }}