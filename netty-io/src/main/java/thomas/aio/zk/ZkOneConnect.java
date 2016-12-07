package thomas.aio.zk;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Created by liubo on 16/7/5.
 */
public class ZkOneConnect implements Watcher{
    private static CountDownLatch latch = new CountDownLatch(1);
    private static ZooKeeper zooKeeper ;

    public static void main(String[] args) {
        try {
            zooKeeper = new ZooKeeper("localhost:2181",600000,new ZkOneConnect());
            System.out.println("ZooKeeper state "+zooKeeper.getState());
            latch.await();
            System.out.println("ZooKeeper session is ok!");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void process(WatchedEvent event) {
        System.out.println("receive watched event: "+event);
        System.out.println(">> "+event.getPath()+" >> "+event.getType());
        if (Event.KeeperState.SyncConnected==event.getState()){
            latch.countDown();
        }
    }

}
