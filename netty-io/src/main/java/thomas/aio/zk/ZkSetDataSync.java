package thomas.aio.zk;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import scala.Int;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Created by liubo on 16/7/10.
 */
public class ZkSetDataSync implements Watcher {
    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    private static ZooKeeper zooKeeper;

    public static void main(String[] args) {
        String path = "/zk-book";
        try {
            zooKeeper = new ZooKeeper(ZkProperties.connectStr,ZkProperties.timeOut,new ZkSetDataSync());
            if (!ZKUtils.nodeExists(zooKeeper,path)){
                zooKeeper.create(path,"123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL);
            }
            zooKeeper.getData(path,true,null);
            Stat stat = zooKeeper.setData(path,"456".getBytes(),-1);
            System.out.println(stat.getCzxid()+","+stat.getMzxid()+","+stat.getVersion());
            Stat stat1 = zooKeeper.setData(path,"456".getBytes(),stat.getVersion());
            System.out.println(stat1.getCzxid()+","+stat1.getMzxid()+","+stat1.getVersion());
            zooKeeper.setData(path,"456".getBytes(),stat.getVersion());
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
            }
        }
    }
}
