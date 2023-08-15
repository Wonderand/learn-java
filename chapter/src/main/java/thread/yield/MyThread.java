package thread.yield;

import thread.daemonThread.Thread1;
import thread.daemonThread.Thread2;

/**
 * 出让线程/礼让线程
 * public static native void yield();
 * 使当前线程放弃当前的CPU资源，让其他的任务去占用CPU执行时间，但是放弃的时间不确定，有可能刚刚放弃，马上又获得CPU时间片
 */
public class MyThread {

    public static void main(String[] args) {

        Thread1 thread1 = new Thread1();
        Thread2 thread2 = new Thread2();

        thread1.setName("坦克");
        thread2.setName("飞机");
        thread1.start();
        thread2.start();
    }
}
