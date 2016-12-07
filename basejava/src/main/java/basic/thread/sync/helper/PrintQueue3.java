package basic.thread.sync.helper;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by liubo on 16/7/18.
 */
public class PrintQueue3 {
    private Semaphore semaphore ;
    private boolean[] freePrinter;
    private Lock lock;

    public PrintQueue3() {
        this.semaphore = new Semaphore(3);
        this.freePrinter = new boolean[3];
        this.lock = new ReentrantLock();
        for (int i = 0; i < freePrinter.length; i++) {
            freePrinter[i] = true;
        }
    }

    private int getPrint(){
        int ret = -1;
        try {
            lock.lock();
            for (int i = 0; i < freePrinter.length; i++) {
                if (freePrinter[i]){
                    ret = i;
                    freePrinter[i] =false;
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        return ret;
    }

    public void printJob(Object obj){
        try {
            semaphore.acquire();
            int assigned = getPrint();
            System.out.println("print job in printer ["+assigned+"] "+Thread.currentThread().getName());
            Thread.sleep((long) (Math.random()*1000));
            freePrinter[assigned] = true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            semaphore.release();
        }
    }
}
