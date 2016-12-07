package basic.thread.distribute.tx;

import java.util.concurrent.CyclicBarrier;

/**
 * Created by liubo on 16/11/2.
 */
public class DistributeTransaction {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2, new Runnable() {
            @Override
            public void run() {
                System.out.println("action to doing");
            }
        });
//        cyclicBarrier.getParties()
    }
}
