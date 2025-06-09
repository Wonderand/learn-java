package thread.A_heima.a2threadcase2;

public class ThreadDemo {

    public static void main(String[] args) {
        /**
         * 多线程的第二种启动方式：
         *  1、自定义一个类实现Runnable接口
         *  2、重写里面的run方法
         *  3、创建自己类的对象
         *  4、创建一个thread对象，并开启线程
         */

        //创建myRun对象
        //表示多线程要执行的任务
        MyRun mr = new MyRun();

        //创建thread对象
        Thread thread1 = new Thread(mr);
        Thread thread2 = new Thread(mr);

        //给现成设置名字
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
