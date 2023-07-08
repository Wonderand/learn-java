package thread.demo;

public class ThreadDemo {

    public static void main(String[] args) {

        MyRun mr = new MyRun();
        Thread thread1 = new Thread(mr);
        Thread thread2 = new Thread(mr);

        thread1.setName("坦克");
        thread2.setName("飞机");
        System.out.println(thread1.getPriority());
        System.out.println(thread2.getPriority());
        thread1.setPriority(1);
        thread2.setPriority(10);
        //开启线程
        thread1.start();
        thread2.start();

    }
}
