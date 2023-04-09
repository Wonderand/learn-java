package thread;

public class test extends Thread implements Runnable{

    public static void main(String[] args) {

//        test test = new test();
//        test.start();
        test1.start();
        for (int i = 0; i < 1000; i++) {
            System.out.println("你好，java!");
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
