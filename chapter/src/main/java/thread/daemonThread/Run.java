package thread.daemonThread;

public class Run {
    public static void main(String[] args) {

        Thread1 thread1 = new Thread1();
        Thread2 thread2 = new Thread2();
        thread1.setName("女神");
        thread2.setName("备胎");

        //把第二个线程开启为守护线程（备胎线程）
        //当其他非守护线程结束后，守护线程也会陆续结束
        thread2.setDaemon(true);
        thread1.setPriority(6);
        thread1.start();
        thread2.start();

    }
}
