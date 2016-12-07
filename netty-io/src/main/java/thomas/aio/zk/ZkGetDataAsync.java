package thomas.aio.zk;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Created by liubo on 16/7/10.
 */
public class ZkGetDataAsync implements Watcher {

    private static CountDownLatch downLatch = new CountDownLatch(1);
    private static ZooKeeper zooKeeper;

    public static void main(String[] args) {
        String path = "/zk-async";
        try {
            zooKeeper = new ZooKeeper(ZkProperties.connectStr,ZkProperties.timeOut,new ZkGetDataAsync());
            downLatch.await();
            zooKeeper.create(path,"123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            zooKeeper.getData(path,true,new IDataCallback(),null);
            zooKeeper.setData(path,"123".getBytes(),-1);
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
                downLatch.countDown();
            }else if (event.getType()== Event.EventType.NodeDataChanged){
                zooKeeper.getData(event.getPath(),true,new IDataCallback(),null);
            }
        }
    }

    private static class IDataCallback implements AsyncCallback.DataCallback {
        @Override
        public void processResult(int rc, String path, Object ctx, byte[] data, Stat stat) {
            System.out.println(rc+","+path+","+new String(data));
            System.out.println(stat.getCzxid()+","+stat.getMzxid()+","+stat.getVersion());
        }
    }
}
