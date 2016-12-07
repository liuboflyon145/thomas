package basic.thread.sync.lock;

/**
 * Created by liubo on 16/7/18.
 */
public class Job implements Runnable {
    private PrintQueue printQueue;

    public Job(PrintQueue printQueue) {
        this.printQueue = printQueue;
    }

    @Override
    public void run() {
        printQueue.printJob(new Object());
    }
}
