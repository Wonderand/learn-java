package thread;

public class implRunnable implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            if (i%2!=0){
                System.out.println(Thread.currentThread().getName()+"=="+i);
            }
        }
    }

    public static void start() {
        // 创建线程并启动
        Thread thread = new Thread(new implRunnable());
        thread.start();
    }
}
