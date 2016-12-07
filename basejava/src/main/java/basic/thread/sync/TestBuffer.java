package basic.thread.sync;

/**
 * Created by liubo on 16/7/17.
 */
public class TestBuffer {
    public static void main(String[] args) {
        FileMock mock = new FileMock(100,10);
        Buffer buffer = new Buffer(20);
        Producer producer = new Producer(mock,buffer);
        Thread producerThread = new Thread(producer,"Producer");

        Consumer[] consumer = new Consumer[3];
        Thread[] consumerThread = new Thread[3];
        for (int i = 0; i < 3; i++) {
            consumer[i] = new Consumer(buffer);
            consumerThread[i] = new Thread(consumer[i],"Consumer"+i);
        }
        producerThread.start();
        for (int i = 0; i < 3; i++) {
            consumerThread[i].start();
        }
    }
}
