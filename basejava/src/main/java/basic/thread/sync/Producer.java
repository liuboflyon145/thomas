package basic.thread.sync;

/**
 * Created by liubo on 16/7/17.
 */
public class Producer implements Runnable{
    private FileMock mock;
    private Buffer buffer;

    public Producer(FileMock mock, Buffer buffer) {
        this.mock = mock;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        buffer.setPendingLines(true);
        while (mock.hasMoreLines()){
            buffer.productData(mock.getLine());
        }
        buffer.setPendingLines(false);
    }
}
