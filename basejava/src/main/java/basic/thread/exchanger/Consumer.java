package basic.thread.exchanger;

import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * Created by liubo on 16/11/21.
 */
public class Consumer implements Runnable {
    private List<String> buffer;
    private final Exchanger<List<String>> exchanger;

    public Consumer(List<String> buffer, Exchanger<List<String>> exchanger) {
        this.buffer = buffer;
        this.exchanger = exchanger;
    }


    @Override
    public void run() {
        int cycle = 1;
        for (int i = 0; i < 10; i++) {
            System.out.printf("consumer: cycle %d \n",cycle);
            try {
                buffer = exchanger.exchange(buffer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("consumer: "+buffer.size());
            for (int j = 0; j < 10; j++) {
                String message = buffer.get(0);
                System.out.printf("consumer: %s\n",message);
                buffer.remove(0);
            }
            cycle++;
        }
    }
}
