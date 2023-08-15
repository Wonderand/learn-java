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
        // setPriority()方法用于设置更改线程的优先级。
        // 每个线程都有一个优先级，由1到10之间的整数表示。
        // Thread类提供3个常量属性：public static int MIN_PRIORITY:它是线程的最大优先级，它的值是1。
        // public static int NORM_PRIORITY:这是线程的普通优先级，它的值是5。
        // public static int MAX_PRIORITY:它是线程的最小优先级，它的值是10。
        // 还可以将线程的优先级设置在1到10之间。
        // 此优先级称为自定义优先级或用户定义的优先级。
        thread1.setPriority(5);
        thread1.start();
        thread2.start();

    }
}
