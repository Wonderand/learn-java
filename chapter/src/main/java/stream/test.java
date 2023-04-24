package stream;

public class test {
    public static void main(String[] args) {

        new Thread(()->{System.out.println("hello");}).start();
    }
}
