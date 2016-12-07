package thomas.aio.zk;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Created by liubo on 16/7/5.
 */
public class ZkCreateNodeSync implements Watcher{
    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) {
        try {
            ZooKeeper zooKeeper = new ZooKeeper(ZkProperties.connectStr,ZkProperties.timeOut,new ZkCreateNodeSync());
            countDownLatch.await();
            String path = zooKeeper.create(ZkProperties.defaultPath,"".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            System.out.println("success create znode "+path);
            path = zooKeeper.create("/zk-test-1","".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL_SEQUENTIAL);
            System.out.println("success create znode "+path);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void process(WatchedEvent event) {
        if (Event.KeeperState.SyncConnected==event.getState()){
            countDownLatch.countDown();
        }
    }
}
