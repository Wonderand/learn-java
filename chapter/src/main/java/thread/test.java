package thread;

/**
 * 继承thread类实现多线程的创建
 */
public class test extends Thread {

    public static void main(String[] args) {

        test t1 = new test();
        t1.start();
        for (int i = 0; i < 1000; i++) {
            if (i%2==0){
                System.out.println(Thread.currentThread().getName()+"=="+i);
            }
        }
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            if (i % 2 == 0){
                System.out.println(Thread.currentThread().getName()+"=="+i);
            }
        }
    }
}
