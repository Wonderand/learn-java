package thread.B_Test.daemonThread;

public class Thread2 extends Thread{
    @Override
    public void run() {

        for (int i = 1; i <= 1000; i++) {
            System.out.println(getName()+"@"+i);
        }
    }
}
