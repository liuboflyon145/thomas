package thomas.aio.zk;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;

/**
 * Created by liubo on 16/7/10.
 */
public class ZKUtils {
    public static boolean nodeExists(ZooKeeper zooKeeper,String path) throws KeeperException, InterruptedException {
        return null!=zooKeeper.exists(path,true);
    }
}
