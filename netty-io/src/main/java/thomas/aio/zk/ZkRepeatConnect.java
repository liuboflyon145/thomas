package thomas.aio.zk;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Created by liubo on 16/7/5.
 */
public class ZkRepeatConnect implements Watcher{
    private static ZooKeeper zooKeeper;
    private static CountDownLatch latch = new CountDownLatch(1);
    private static final String  connectStr = "127.0.0.1:2181";
    private static int timeOut = 50000;
    public static void main(String[] args) {
        try {
            zooKeeper = new ZooKeeper(connectStr,timeOut,new ZkRepeatConnect());
            latch.await();
            long sessionId = zooKeeper.getSessionId();
            byte[] pwd =  zooKeeper.getSessionPasswd();

            zooKeeper = new ZooKeeper(connectStr,timeOut,new ZkRepeatConnect(),1,"test".getBytes());
            zooKeeper = new ZooKeeper(connectStr,timeOut,new ZkRepeatConnect(),sessionId,pwd);
            Thread.sleep(Integer.MAX_VALUE);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void process(WatchedEvent event) {
        if (Event.KeeperState.SyncConnected==event.getState()){
            System.out.println("receive event is "+event);
            System.out.println("<<<<<<<<<<<<<"+Thread.currentThread().getName()+">>>>>>>>>>>");
            latch.countDown();
        }
    }
}
