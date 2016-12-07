package thomas.aio.zk.auth;

import org.apache.zookeeper.*;
import thomas.aio.zk.ZKUtils;
import thomas.aio.zk.ZkProperties;

import java.io.IOException;

/**
 * Created by liubo on 16/7/11.
 */
public class AuthSampleGet1 implements Watcher {
    static final String path = "/zk-book-auth_test";

    public static void main(String[] args) {
        try {
            ZooKeeper zooKeeper = new ZooKeeper(ZkProperties.connectStr,ZkProperties.timeOut,new AuthSampleGet1());
            zooKeeper.addAuthInfo("digest","foo:true".getBytes());
            if (!ZKUtils.nodeExists(zooKeeper,path)){
                zooKeeper.create(path,"init".getBytes(), ZooDefs.Ids.CREATOR_ALL_ACL, CreateMode.PERSISTENT);
            }
            System.out.println(new String (zooKeeper.getData(path,false,null)));
            ZooKeeper zooKeeper1= new ZooKeeper(ZkProperties.connectStr,ZkProperties.timeOut,new AuthSampleGet1());
            zooKeeper1.addAuthInfo("digest","foo:true".getBytes());
            System.out.println(new String (zooKeeper1.getData(path,false,null)));
            ZooKeeper zooKeeper2 = new ZooKeeper(ZkProperties.connectStr,ZkProperties.timeOut,new AuthSampleGet1());
            zooKeeper2.addAuthInfo("digest","foo:false".getBytes());
            zooKeeper2.getData(path,true,null);
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
        System.out.println("111111"+event.getType());
    }
}
