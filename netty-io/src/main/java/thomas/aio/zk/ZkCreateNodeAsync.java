package thomas.aio.zk;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Created by liubo on 16/7/5.
 */
public class ZkCreateNodeAsync implements Watcher {
    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) {
        try {
            ZooKeeper zooKeeper = new ZooKeeper(ZkProperties.connectStr,ZkProperties.timeOut,new ZkCreateNodeAsync());
            countDownLatch.await();

            zooKeeper.create(ZkProperties.defaultPath, "zkTest".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL, new ResultCallBack(),"the default znode");
            zooKeeper.create("/zk-test-1","zk-test-1".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL,new ResultCallBack(),"zk-test-1");
            zooKeeper.create("/zk-test-2","".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL_SEQUENTIAL,new ResultCallBack(),"null");
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
            System.out.println("success to connect");
            countDownLatch.countDown();
        }
    }

    private static class ResultCallBack implements AsyncCallback.StringCallback {
        @Override
        public void processResult(int rc, String path, Object ctx, String name) {
            System.out.println("create path result is [rc= "+rc+" ,path= "+path+" ,ctx='"+ctx+"',name='"+name+"']");
        }
    }
}
