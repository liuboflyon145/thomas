package basic.thread.executor;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by liubo on 16/7/20.
 */
public class TestTask {
    public static void main(String[] args) {
        Server server = new Server();
//        Optional optional =Optional.ofNullable(server);
//        System.out.println(optional.isPresent());
        for (int i = 0; i < 100; i++) {
            DataTask task = new DataTask("task "+i);
            task.setData(new AtomicInteger(i));
            server.executeTask(task);
        }
    }
}
