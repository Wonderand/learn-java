package com.huzhirong.voice_demo.test;

import javax.sound.sampled.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class demo3 extends Thread {
    static TargetDataLine line = null;
    public static void main(String[] args) {

        demo3 demo = new demo3();
        try {
            demo.startStream();
            demo.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void startStream() throws Exception {
        long startTime = System.currentTimeMillis();
        System.out.println("开始录音: "+startTime);
        //创建音频格式
        AudioFormat audioFormat = new AudioFormat(44100, 16, 2, true, true);
        //创建数据行的信息对象
        DataLine.Info dataLineInfo = new DataLine.Info(TargetDataLine.class, audioFormat);
        //获取与指定的Line.Info对象匹配的行
        line = (TargetDataLine) AudioSystem.getLine(dataLineInfo);
        //打开具有指定格式的行，这样可使行获得所有所需的系统资源并变得可操作
        line.open(audioFormat);
        //start()方法开始获取音频数据
        line.start();

        if (line.isRunning()) {
            System.out.println("开始录音");
        }
        save(new File("E:\\jdk17\\test\\voice_demo\\src\\main\\resources\\a.wav"),line);
        System.out.println("录音结束");
        if (System.currentTimeMillis() - startTime >= 10000){
            line.stop();
            line.close();
        }
        line.stop();
        line.close();
    }
    public void readByte(TargetDataLine line){
        byte[] buffer = new byte[4096];
        while (line.isOpen()) {
            int count = line.read(buffer, 0, buffer.length);
            if (count > 0) {
                System.out.println(buffer.toString());
                // process the data
            }
        }
    }

    public void save(File file,TargetDataLine targetDataLine) throws IOException {
        AudioFileFormat.Type fileType = AudioFileFormat.Type.WAVE;
        AudioInputStream audioInputStream = new AudioInputStream(targetDataLine);
        AudioSystem.write(audioInputStream, fileType, file);
        if (file.exists()) {
            System.out.println("录音文件保存成功");
        }
    }

    @Override
    public void run() {
        readByte(line);
    }
}
