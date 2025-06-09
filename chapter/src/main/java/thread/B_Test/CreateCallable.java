package thread.B_Test;

import java.util.concurrent.Callable;

/**
 * 扩展性强，实现该接口的同时还可以继承其他类
 */
public class CreateCallable implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        //求1~100的和
        int sum = 0;
        for (int i = 0; i < 100; i++) {
            sum = sum + 1;
        }
        return sum;
    }
}
