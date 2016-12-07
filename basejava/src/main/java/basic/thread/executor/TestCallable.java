package basic.thread.executor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by liubo on 16/7/21.
 */
public class TestCallable {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
        List<Future<Integer>> futureList = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            Integer integer = random.nextInt(10);
            FactorialCalcuCallable calcuCallable = new FactorialCalcuCallable(integer);
            futureList.add(executor.submit(calcuCallable));
        }
        executor.shutdown();
        for (int i = 0; i < 10; i++) {
            Future future = futureList.get(i);
            try {
                System.out.printf("Main task %d: %s ,%s\n",i,future.isDone(),future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
