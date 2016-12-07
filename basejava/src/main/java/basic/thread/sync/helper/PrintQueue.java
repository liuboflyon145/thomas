package basic.thread.sync.helper;

import java.util.concurrent.Semaphore;

/**
 * Created by liubo on 16/7/18.
 */
public class PrintQueue {
    private final Semaphore semaphore;

    public PrintQueue() {
        this.semaphore = new Semaphore(1);
    }

    public void printJob(Object obj){
        try {
            semaphore.acquire();
            System.out.println("print job "+Thread.currentThread().getName());
//            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            semaphore.release();
        }
    }
}
