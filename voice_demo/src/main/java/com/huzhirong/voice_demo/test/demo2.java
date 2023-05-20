package com.huzhirong.voice_demo.test;

import javax.sound.sampled.*;

public class demo2 {

    public void start() throws LineUnavailableException {
        AudioFormat audioFormat = new AudioFormat(44100, 16, 2, true, true);
        DataLine.Info dataLineInfo = new DataLine.Info(TargetDataLine.class, audioFormat);
        targetDataLine = (TargetDataLine) AudioSystem.getLine(dataLineInfo);
        targetDataLine.open(audioFormat);
        targetDataLine.start();

        byte[] buffer = new byte[1024];
        int bytesRead;
        while (true) {
            bytesRead = targetDataLine.read(buffer, 0, buffer.length);
            // Do something with the audio data here
        }
    }

    public void stop() {
        targetDataLine.stop();
        targetDataLine.close();
    }
}
