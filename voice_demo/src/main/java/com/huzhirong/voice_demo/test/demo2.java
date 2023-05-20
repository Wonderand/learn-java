package com.huzhirong.voice_demo.test;

import javax.sound.sampled.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class demo2 {

    public void start() throws LineUnavailableException, IOException {
        //采样率是每秒播放和录制的样本数
        AudioFormat audioFormat = new AudioFormat(44100, 16, 2, true, true);
        //通过AudioSystem获取本地音频混合器信息
        DataLine.Info dataLineInfo = new DataLine.Info(TargetDataLine.class, audioFormat);
        //通过设置好的音频编解码器获取数据线信息
        TargetDataLine line = (TargetDataLine) AudioSystem.getLine(dataLineInfo);
        //开启音频
        line.open(audioFormat);
        //start()方法开始获取音频数据
        line.start();
        //创建缓冲区
        byte[] buffer = new byte[1024];
//        FileOutputStream fw = new FileOutputStream("E:\\jdk17\\test\\voice_demo\\src\\main\\resources\\a.wav");
        //判断是否停止录音
        if (line.isRunning()) {
            System.out.println("开始录音");
        }
        File file = new File("E:\\jdk17\\test\\voice_demo\\src\\main\\resources\\a.wav");
        int bytesRead;
        long startTime = System.currentTimeMillis();
        while (true) {
            String s = new Scanner(System.in).nextLine();
            // 1. 创建字节输入流对象
            bytesRead = line.read(buffer, 0, buffer.length);
            System.out.println(bytesRead);
            AudioFileFormat.Type fileType = AudioFileFormat.Type.WAVE;
            // 2. 创建字节输出流对象
            AudioInputStream audioInputStream = new AudioInputStream(line);
            try {
                AudioSystem.write(audioInputStream, fileType, file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (s.equals("开始录音")) {
                stop(line);
                break;
            }
            // Do something with the audio data here
        }
        //超过10秒自动停止录音
        if (System.currentTimeMillis() - startTime >= 10000) {
            stop(line);
        }
        System.out.println("录音结束");
    }

    public void stop(TargetDataLine line) {
        line.stop();
        line.close();
    }

    public static void main(String[] args) {
        demo2 demo = new demo2();
        try {
            demo.start();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
