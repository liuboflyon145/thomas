package basic.thread.sync.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by liubo on 16/7/19.
 */
public class Searcher implements Runnable{
    private int firstRow;
    private int lastRow;
    private MatrixMock mock;
    private Results results;
    private int number;

    private final CyclicBarrier barrier;

    public Searcher(CyclicBarrier barrier, int firstRow, int lastRow, MatrixMock mock, Results results, int number) {
        this.barrier = barrier;
        this.firstRow = firstRow;
        this.lastRow = lastRow;
        this.mock = mock;
        this.results = results;
        this.number = number;
    }

    @Override
    public void run() {
        int counter = 0;
        System.out.printf("%s 查找范围从%s到%s\n",Thread.currentThread().getName(),firstRow,lastRow);
        for (int i = firstRow; i < lastRow; i++) {
            int row[] = mock.getRow(i);
            for (int j = 0; j < row.length; j++) {
                if (row[j]==number){
                    counter++;
                }
            }
            results.setData(i,counter);
        }
        System.out.printf("% 查找完成,进入等待中\n",Thread.currentThread().getName());
        try {
            barrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
