package basic.thread.executor;

import java.util.concurrent.Callable;

/**
 * Created by liubo on 16/7/21.
 */
public class FactorialCalcuCallable implements Callable<Integer> {
    private Integer number;

    public FactorialCalcuCallable(Integer number) {
        this.number = number;
    }

    @Override
    public Integer call() throws Exception {
        int result = 1;
        if (number==0||number==1){
            result = 1;
        }else {
            for (int i = 2; i <= number; i++) {
                result*=1;
            }
        }
        System.out.printf("%s: %d\n",Thread.currentThread().getName(),result);
        return result;
    }
}
