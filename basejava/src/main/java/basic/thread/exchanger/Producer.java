package basic.thread.exchanger;

import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * Created by liubo on 16/11/21.
 */
public class Producer implements Runnable {
    private List<String > buffer;
    private final Exchanger<List<String>> exchanger;

    public Producer(List<String> buffer, Exchanger<List<String>> exchanger) {
        this.buffer = buffer;
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        int cycle = 1;
        for (int i = 0; i < 10; i++) {
            System.out.printf("producer: cycle %d \n",cycle);
            for (int j = 0; j < 10; j++) {
                String message = "Event "+((i*10)+j);
                buffer.add(message);
                System.out.printf("producer: %s\n",message);
            }
            try {
                buffer = exchanger.exchange(buffer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("producer: "+buffer.size());
            cycle++;
        }

    }
}
