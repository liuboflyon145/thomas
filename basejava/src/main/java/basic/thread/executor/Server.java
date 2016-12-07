package basic.thread.executor;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by liubo on 16/7/20.
 */
public class Server  {
    private ThreadPoolExecutor executor ;

    public Server() {
        this.executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
    }

    public void executeTask(DataTask task){
        executor.execute(task);
        System.out.printf("pool size:%d ,active count:%d ,completed task %d, core pool size:%d\n",executor.getPoolSize(),executor.getActiveCount(),executor.getCompletedTaskCount(),executor.getCorePoolSize());
//        shutDownServer();
    }
    public void shutDownServer(){
        executor.shutdown();
    }
}
