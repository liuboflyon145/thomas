package thomas.aio.zk;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Created by liubo on 16/7/10.
 */
public class ZkGetDataSync implements Watcher {
    private static CountDownLatch downLatch = new CountDownLatch(1);
    private static ZooKeeper zooKeeper;
    private static Stat stat=new Stat();

    public static void main(String[] args) {
        String path = "/zk-data1";
        try {
            zooKeeper = new ZooKeeper(ZkProperties.connectStr,ZkProperties.timeOut,new ZkGetDataSync());
            downLatch.await();
            zooKeeper.create(path,"123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            System.out.println(new String(zooKeeper.getData(path,true,stat)));
            System.out.println(stat.getCzxid()+","+stat.getMzxid()+","+stat.getVersion());
            zooKeeper.setData(path,"123".getBytes(),-1);
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
                downLatch.countDown();
            }else if (event.getType()== Event.EventType.NodeDataChanged){
                try {
                    System.out.println(new String(zooKeeper.getData(event.getPath(),true,stat)));
                    System.out.println(stat.getCzxid()+","+stat.getMzxid()+","+stat.getVersion());
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ;
            }
        }
    }
}
