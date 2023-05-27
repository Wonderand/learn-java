package com.huzhirong.voice_demo.test;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class RecordThread extends Thread {
    private volatile boolean running = true;
    private AudioInputStream audioInputStream;
    private AudioFileFormat.Type fileType;
    private File file;

    public RecordThread(AudioInputStream audioInputStream, AudioFileFormat.Type fileType, File file) {
        this.audioInputStream = audioInputStream;
        this.fileType = fileType;
        this.file = file;
    }

    public void stopRunning() {
        running = false;
    }

    @Override
    public void run() {
        try {
            AudioSystem.write(audioInputStream, fileType, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (running) {
            System.out.println(running);
            // do something
        }
    }
}

class Main {
    public static void main(String[] args) throws Exception {
        AudioFormat audioFormat = new AudioFormat(44100, 16, 2, true, true);
        //创建数据行的信息对象
        DataLine.Info dataLineInfo = new DataLine.Info(TargetDataLine.class, audioFormat);
        //获取与指定的Line.Info对象匹配的行
        TargetDataLine line = (TargetDataLine) AudioSystem.getLine(dataLineInfo);

        AudioInputStream audioInputStream = new AudioInputStream(line);

        Scanner scanner = new Scanner(System.in);
        RecordThread recordThread = null;
        while (scanner.hasNextLine()) {
            String linetxt = scanner.nextLine();
            if ("开始录音".equals(linetxt)) {
                recordThread = new RecordThread(audioInputStream, AudioFileFormat.Type.WAVE, new File("E:\\jdk17\\test\\voice_demo\\src\\main\\resources\\a.wav"));
                recordThread.start();
            } else if ("录音结束".equals(line)) {
                recordThread.stopRunning();
                System.exit(0);
            }
        }
    }
}
