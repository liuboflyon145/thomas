package basic.thread.exchanger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * Created by liubo on 16/11/21.
 */
public class TestMain {
    public static void main(String[] args) {
        List<String> producer = new ArrayList<String>();
        List<String> consumer = new ArrayList<String>();

        Exchanger<List<String>> exchanger = new Exchanger<>();

        Producer producer1 = new Producer(producer,exchanger);
        Consumer consumer1 = new Consumer(consumer,exchanger);

        new Thread(producer1).start();
        new Thread(consumer1).start();
    }
}
