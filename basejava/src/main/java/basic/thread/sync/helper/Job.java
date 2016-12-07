package basic.thread.sync.helper;

/**
 * Created by liubo on 16/7/18.
 */
public class Job implements Runnable{
    private PrintQueue printQueue;

    private PrintQueue3 printQueue3;

    public Job(PrintQueue3 printQueue3) {
        this.printQueue3 = printQueue3;
    }

    public Job(PrintQueue printQueue) {
        this.printQueue = printQueue;
    }


    @Override
    public void run() {
        printQueue3.printJob(new Object());
        printQueue.printJob(new Object());
    }
}
