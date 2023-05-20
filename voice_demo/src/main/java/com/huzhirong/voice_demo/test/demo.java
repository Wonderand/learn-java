package com.huzhirong.voice_demo.test;

import org.springframework.beans.factory.annotation.Autowired;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class demo {

    private TargetDataLine targetDataLine;

    public static void main(String[] args) {
        demo demo = new demo();
        try {
            demo.start();
            Thread.sleep(5000);
            demo.stop();
            demo.save(new File("E:\\jdk17\\test\\voice_demo\\src\\main\\resources\\a.wav"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void start() throws Exception {
        AudioFormat audioFormat = new AudioFormat(44100, 16, 2, true, true);
        DataLine.Info info = new DataLine.Info(TargetDataLine.class, audioFormat);
        Mixer.Info[] mixerInfos = AudioSystem.getMixerInfo();
        Mixer mixer = AudioSystem.getMixer(mixerInfos[0]);
        targetDataLine = (TargetDataLine) mixer.getLine(info);
        targetDataLine.open(audioFormat);
        targetDataLine.start();
    }

    public void stop() throws Exception {
        targetDataLine.stop();
        targetDataLine.close();
    }

    public void save(File file) throws IOException {
        AudioFileFormat.Type fileType = AudioFileFormat.Type.WAVE;
        AudioInputStream audioInputStream = new AudioInputStream(targetDataLine);
        AudioSystem.write(audioInputStream, fileType, file);
    }

}
