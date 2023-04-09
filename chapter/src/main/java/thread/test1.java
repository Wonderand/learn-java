package thread;

public class test1  implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            if (i%2!=0){
                System.out.println(Thread.currentThread().getName()+"=="+i);
            }
        }
    }

    public static void start() {
        new test1().start();
    }
}
