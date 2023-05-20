package com.huzhirong.voice_demo.test;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
public class demo3 {
    public static void main(String[] args) {

        demo3 demo = new demo3();
        try {
            demo.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void start() throws Exception {
        long startTime = System.currentTimeMillis();
        System.out.println("开始录音: "+startTime);
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
        if (line.isRunning()) {
            System.out.println("开始录音");
        }
        save(new File("E:\\jdk17\\test\\voice_demo\\src\\main\\resources\\a.wav"),line);
        System.out.println("录音结束");
        if (System.currentTimeMillis() - startTime >= 10000){
            line.stop();
            line.close();
        }
//        line.stop();
//        line.close();
    }

    public void save(File file,TargetDataLine targetDataLine) throws IOException {
        AudioFileFormat.Type fileType = AudioFileFormat.Type.WAVE;
        AudioInputStream audioInputStream = new AudioInputStream(targetDataLine);
        AudioSystem.write(audioInputStream, fileType, file);
    }
}
