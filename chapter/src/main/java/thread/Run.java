package thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Run {

    public static void main(String[] args) {

        // 创建callable对象（表示线程要执行的任务）
        CreateCallable cc = new CreateCallable();
        // 创建FutureTask的对象（作用多线程运行的结果）
        FutureTask<Integer> ft = new FutureTask<>(cc);
        // 创建线程对象
        Thread thread = new Thread(ft);
        thread.start();
        try {
            Integer result = ft.get();
            System.out.println(result);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

    }
}
