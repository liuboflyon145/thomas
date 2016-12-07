package basic.thread.executor;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by liubo on 16/7/20.
 */
public class DataTask implements Runnable {
    private AtomicInteger data ;
    private String name;

    public DataTask(String name) {
        this.data = new AtomicInteger(0);
        this.name = name;
    }

    public AtomicInteger getData() {
        return data;
    }

    public void setData(AtomicInteger data) {
        this.data = data;
    }

    @Override
    public void run() {
        try {
            data.getAndAdd(5);
            System.out.println("current data is :"+data);
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
