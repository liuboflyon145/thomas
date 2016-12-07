package thomas.aio.zk;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Created by liubo on 16/7/10.
 */
public class ZkNodeExist implements Watcher{
    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    private static ZooKeeper zooKeeper;

    public static void main(String[] args) {
        String path = "/zk-book1";
        try {
            zooKeeper = new ZooKeeper(ZkProperties.connectStr,ZkProperties.timeOut,new ZkNodeExist());
            countDownLatch.await();
            zooKeeper.exists(path,true);
            if (!ZKUtils.nodeExists(zooKeeper,path)){
                zooKeeper.create(path,"123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
            }
            zooKeeper.setData(path,"456".getBytes(),-1);
            zooKeeper.create(path+"/c1","".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
            zooKeeper.delete(path+"/c1",-1);
            zooKeeper.delete(path,-1);
            Thread.sleep(Integer.MAX_VALUE);
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
            if (Event.EventType.None==event.getType()&&null==event.getPath()){
                countDownLatch.countDown();
            }else if (Event.EventType.NodeCreated==event.getType()){
                System.out.println("Node("+event.getPath()+") Created" );
                try {
                    zooKeeper.exists(event.getPath(),true);
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else if (Event.EventType.NodeDeleted==event.getType()){
                System.out.println("Node("+event.getPath()+") Deleted");
                try {
                    zooKeeper.exists(event.getPath(),true);
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else if (Event.EventType.NodeDataChanged==event.getType()){
                System.out.println("Node("+event.getPath()+") DataChanged");
                try {
                    zooKeeper.exists(event.getPath(),true);
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
