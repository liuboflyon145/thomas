package basic.thread.sync.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by liubo on 16/7/18.
 */
public class PrintQueue {
    private final Lock queueLock = new ReentrantLock();
    public void printJob(Object document){
        queueLock.lock();
        Long duration = (long)Math.random()*10000;
        System.out.println(Thread.currentThread().getName()+": printQueue:"+(duration/1000));
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            queueLock.unlock();
        }
    }
}
