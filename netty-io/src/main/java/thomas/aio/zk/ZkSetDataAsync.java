package thomas.aio.zk;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Created by liubo on 16/7/10.
 */
public class ZkSetDataAsync implements Watcher {
    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    private static ZooKeeper zooKeeper;

    public static void main(String[] args) {
        String path = "/zk-book";
        try {
            zooKeeper = new ZooKeeper(ZkProperties.connectStr,ZkProperties.timeOut,new ZkSetDataAsync());
            countDownLatch.await();
            if (!ZKUtils.nodeExists(zooKeeper,path)){
                zooKeeper.create(path,"123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL);
            }
            System.out.println("111111");
            zooKeeper.setData(path,"456".getBytes(),-1,new IStatCallback(),null);
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
                System.out.println("22222222");
                countDownLatch.countDown();
            }
        }
    }

    private static class IStatCallback implements AsyncCallback.StatCallback {
        @Override
        public void processResult(int rc, String path, Object ctx, Stat stat) {
            if (rc==0){
                System.out.println("3333333");
                System.out.println("SUCCESS");
            }
        }
    }
}
