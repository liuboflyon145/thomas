package basic.data.sync;

import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockTest {
    static final int WRITER = 1;
    static final int READER = 3;
    public static void main(String[] args) {  
        final Data data = new Data();  
        for (int i = 0; i < 3; i++) {  
            new Thread(new Runnable() {  
                public void run() {  
                    for (int j = 0; j < 5; j++) {  
                        data.set(new Random().nextInt(30));
                    }  
                }  
            }).start();  
        }         
        for (int i = 0; i < 3; i++) {  
            new Thread(new Runnable() {  
                public void run() {  
                    for (int j = 0; j < 5; j++) {  
                        data.get();  
                    }  
                }  
            }).start();  
        }  
    }  
}  
class Data {
    ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private int data;// 共享数据      
    public void set(int data) {

        try {
            readWriteLock.writeLock().lock();
            System.out.println(Thread.currentThread().getName() + "准备写入数据");
//            Thread.sleep(20);
            this.data = data;
            System.out.println(Thread.currentThread().getName() + "写入" + this.data);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            readWriteLock.writeLock().unlock();
        }

    }     
    public void get() {
        try {
            readWriteLock.readLock().lock();
            System.out.println(Thread.currentThread().getName() + "准备读取数据");
//                Thread.sleep(20);
            System.out.println(Thread.currentThread().getName() + "读取" + this.data);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            readWriteLock.readLock().unlock();
        }

    }  
}  