package thomas.aio.zk;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by liubo on 16/7/7.
 */
public class ZkGetChildrenAsync implements Watcher{
    private static CountDownLatch downLatch = new CountDownLatch(1);
    private static ZooKeeper zooKeeper =null;

    public static void main(String[] args) {
        try {
            String path = "/zk-book2";
            zooKeeper = new ZooKeeper(ZkProperties.connectStr,ZkProperties.timeOut,new ZkGetChildrenAsync());
            downLatch.await();

            if (!existsPath(path)){
                zooKeeper.create(path,"".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
            if (!existsPath(path+"/c1")){
                zooKeeper.create(path+"/c1","".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL);
            }
            zooKeeper.getChildren(path,true,new IChildren2Callback(),null);
            if (!existsPath(path+"/c2")){
                zooKeeper.create(path+"/c2","".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL);
            }
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

    private static class IChildren2Callback implements  AsyncCallback.Children2Callback {
        @Override
        public void processResult(int rc, String path, Object ctx, List<String> children, Stat stat) {
            System.out.println("get children znode result : [response code :"+rc+",param path :"+path+", ctx:"+ctx+", children list:"+children+",stat:"+stat);
        }

    }
}
