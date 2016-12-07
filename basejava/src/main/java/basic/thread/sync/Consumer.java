package basic.thread.sync;

import java.util.Random;

/**
 * Created by liubo on 16/7/17.
 */
public class Consumer implements Runnable{
    private Buffer buffer;

    public Consumer(Buffer buffer) {
        this.buffer = buffer;
    }

    private void processLine(String line){
        try {
            Random random = new Random();
            Thread.sleep(random.nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (buffer.hasPendingLines()){
            processLine(buffer.consumerData());
        }
    }
}
