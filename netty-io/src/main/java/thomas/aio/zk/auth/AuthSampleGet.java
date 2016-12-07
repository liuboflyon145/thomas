package thomas.aio.zk.auth;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import thomas.aio.zk.ZkProperties;

import java.io.IOException;

/**
 * Created by liubo on 16/7/10.
 */
public class AuthSampleGet {
    static final String path = "/zk-book-auth_test";

    public static void main(String[] args) {
        try {
            ZooKeeper zooKeeper =new ZooKeeper(ZkProperties.connectStr,ZkProperties.timeOut,null);
            zooKeeper.addAuthInfo("digest","foo:true".getBytes());
            zooKeeper.create(path,"init".getBytes(), ZooDefs.Ids.CREATOR_ALL_ACL, CreateMode.EPHEMERAL);
            ZooKeeper zooKeeper1 = new ZooKeeper(ZkProperties.connectStr,ZkProperties.timeOut,null);
            zooKeeper1.getData(path,false,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
