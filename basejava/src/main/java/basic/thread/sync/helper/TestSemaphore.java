package basic.thread.sync.helper;

/**
 * Created by liubo on 16/7/18.
 */
public class TestSemaphore {
    public static void main(String[] args) {
//        PrintQueue printQueue = new PrintQueue();
        PrintQueue3 printQueue = new PrintQueue3();
        Thread[] threads = new Thread[10];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new Job(printQueue),"Thread :"+i);
        }

        for (Thread t :threads) {
            t.start();
        }
    }
}
