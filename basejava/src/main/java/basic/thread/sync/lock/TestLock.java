package basic.thread.sync.lock;

/**
 * Created by liubo on 16/7/18.
 */
public class TestLock {
    public static void main(String[] args) {
        PrintQueue printQueue = new PrintQueue();
        Thread[] threads = new Thread[10];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new Job(printQueue),"thread "+i);
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }
    }
}
