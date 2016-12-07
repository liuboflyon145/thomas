package basic.thread.sync;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by liubo on 16/7/17.
 */
public class Buffer {
    private LinkedList<String > buffer;
    private int maxSize;
    private ReentrantLock lock;
    private Condition product;
    private Condition consumer;
    private boolean pendingLines;

    public Buffer(int maxSize) {
        this.maxSize = maxSize;
        this.buffer = new LinkedList<>();
        this.lock = new ReentrantLock();
        this.product = lock.newCondition();
        this.consumer = lock.newCondition();
        this.pendingLines = true;
    }

    public void productData(String line){
        lock.lock();
        try {
            while (buffer.size()==maxSize){
                product.await();
            }
            buffer.offer(line);
            System.out.printf("%s: inserted line: %d\n",Thread.currentThread().getName(),buffer.size());
            consumer.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public String consumerData(){
        String line = null;
        lock.lock();
        try {
            while (buffer.size()==0&&hasPendingLines()){
                consumer.await();
            }
            if (hasPendingLines()){
                line = buffer.poll();
                System.out.printf("%s: Line Readed: %d\n",Thread.currentThread().getName(),buffer.size());
                product.signalAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        return line;
    }

    public void setPendingLines(boolean pendingLines){
        this.pendingLines = pendingLines;
    }

    public boolean hasPendingLines(){
        return pendingLines||buffer.size()>0;
    }
}
