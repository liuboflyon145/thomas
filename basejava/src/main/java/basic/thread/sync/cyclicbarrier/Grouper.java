package basic.thread.sync.cyclicbarrier;

/**
 * Created by liubo on 16/7/19.
 */
public class Grouper implements Runnable{
    private Results results;

    public Grouper(Results results) {
        this.results = results;
    }

    @Override
    public void run() {
        int finalResult = 0;
        System.out.println("组任务进行中。。。。。。。");
        int[] data = results.getData();
        for (int number:data){
            finalResult +=number;
        }
        System.out.printf("总结果未:%d",finalResult);
    }
}
