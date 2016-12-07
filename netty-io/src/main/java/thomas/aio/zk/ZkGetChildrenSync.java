package thomas.aio.zk;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by liubo on 16/7/7.
 */
public class ZkGetChildrenSync implements Watcher{
    private static CountDownLatch downLatch = new CountDownLatch(1);
    private static ZooKeeper zooKeeper = null;

    public static void main(String[] args) {
        try {
            String path = "/zk-book";
            zooKeeper = new ZooKeeper(ZkProperties.connectStr,ZkProperties.timeOut,new ZkGetChildrenSync());
            downLatch.await();

            if (!existsPath(path)) {
                zooKeeper.create(path, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
            if (!existsPath(path+"/c1")){
                zooKeeper.create(path+"/c1","".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL);
            }

            List<String > childrenList = zooKeeper.getChildren(path,true);
            System.out.println("@@@@@@@@:"+childrenList.toString());
            if (!existsPath(path+"/c2")){
                zooKeeper.create(path+"/c2","".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL);
            }

            Thread.sleep(Integer.MAX_VALUE);


        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }

    private static boolean existsPath(String node) {
        try {
            return null!=zooKeeper.exists(node,true);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void process(WatchedEvent event) {
        if (Event.KeeperState.SyncConnected==event.getState()){
            if (Event.EventType.None==event.getType()&&null==event.getPath()){
                downLatch.countDown();
            }else if (event.getType()== Event.EventType.NodeChildrenChanged){
                try {
                    System.out.println("ReGet child"+zooKeeper.getChildren(event.getPath(),true));
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
